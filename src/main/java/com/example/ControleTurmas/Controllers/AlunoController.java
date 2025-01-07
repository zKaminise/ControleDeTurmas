package com.example.ControleTurmas.Controllers;

import com.example.ControleTurmas.Entity.AdultoResponsavel;
import com.example.ControleTurmas.Entity.Alunos;
import com.example.ControleTurmas.Enums.TurmasEnum;
import com.example.ControleTurmas.Repositorys.AlunoRepository;
import com.example.ControleTurmas.Services.PdfGenerator;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.HtmlUtils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private PdfGenerator pdfGenerator;

    @PostMapping
    public ResponseEntity<Alunos> criarAluno(@RequestBody Alunos alunos) {
        System.out.println("JSON recebido: " + alunos);
        System.out.println("Turma recebida: " + alunos.getTurmasEnum());

        if (alunos.getTurmasEnum() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Campo 'turma' é obrigatório.");
        }
        // Associar cada adulto responsável ao aluno
        if (alunos.getAdultosResponsaveis() != null) {
            for (AdultoResponsavel responsavel : alunos.getAdultosResponsaveis()) {
                responsavel.setAluno(alunos);
            }
        }
        // Salvar o aluno junto com os adultos responsáveis

        Alunos novoAluno = alunoRepository.save(alunos);
        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping
    public ResponseEntity<List<Alunos>> listarAlunos() {
        List<Alunos> alunos = alunoRepository.findAll().stream().sorted((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome())).toList();
        return ResponseEntity.ok(alunos);
    }

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "")
                .toLowerCase();
    }

    @GetMapping("/check-nome")
    public ResponseEntity<Boolean> verificarNome(@RequestParam String nome) {
        String nomeNormalizado = normalize(nome);
        List<Alunos> alunos = alunoRepository.findAll();
        boolean exists = alunos.stream()
                .anyMatch(aluno -> normalize(aluno.getNome()).equals(nomeNormalizado));
        return ResponseEntity.ok(exists);
    }

    // Endpoint para listar alunos de uma turma específica
//    @GetMapping("/turma/{turma}")
//    public ResponseEntity<List<Alunos>> listarAlunosPorTurma(@PathVariable String turma) {
//        List<Alunos> alunos = alunoRepository.findAll()
//                .stream()
//                .filter(aluno -> aluno.getTurmasEnum().toString().equalsIgnoreCase(turma))
//                .sorted((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome()))
//                .toList();
//        return ResponseEntity.ok(alunos);
//    }
    @GetMapping("/turma/{turma}")
    public ResponseEntity<List<Alunos>> listarAlunosPorTurma(@PathVariable TurmasEnum turma) {
        List<Alunos> alunos = alunoRepository.findAlunosByTurma(turma);
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alunos> getAluno(@PathVariable Long id) {
        return alunoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alunos> atualizarAluno(@PathVariable Long id, @RequestBody Alunos alunosAtualizados) {
        return alunoRepository.findById(id)
                .map(aluno -> {
                    // Atualizar informações básicas do aluno
                    aluno.setNome(alunosAtualizados.getNome());
                    aluno.setTelefone(alunosAtualizados.getTelefone());
                    aluno.setTransporteEscolar(alunosAtualizados.getTransporteEscolar());
                    aluno.setTurmasEnum(alunosAtualizados.getTurmasEnum());

                    // Atualizar adultos responsáveis
                    aluno.getAdultosResponsaveis().clear(); // Limpar os adultos atuais
                    if (alunosAtualizados.getAdultosResponsaveis() != null) {
                        for (AdultoResponsavel responsavel : alunosAtualizados.getAdultosResponsaveis()) {
                            responsavel.setAluno(aluno); // Reassociar ao aluno
                            aluno.getAdultosResponsaveis().add(responsavel); // Adicionar à lista
                        }
                    }

                    // Salvar o aluno atualizado
                    Alunos atualizado = alunoRepository.save(aluno);
                    return ResponseEntity.ok(atualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirAluno(@PathVariable Long id) {
        if (!alunoRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluno não encontrado.");
        }
        alunoRepository.deleteById(id);
        return ResponseEntity.ok("Aluno excluído com sucesso!");
    }

    @GetMapping("/relatorio/{turma}")
    public void gerarRelatorio(@PathVariable String turma, HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio_alunos.pdf");

        // Normalizar o valor da turma recebida (remover espaços e converter para maiúsculas)
        String normalizedTurma = turma.replace(" ", "").toUpperCase();

        // Encontrar o valor correspondente no enum
        TurmasEnum turmaEnum = Arrays.stream(TurmasEnum.values())
                .filter(e -> e.name().equalsIgnoreCase(normalizedTurma))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Turma inválida: " + turma));

        // Buscar os alunos da turma
        List<Alunos> alunos = alunoRepository.findAlunosByTurma(turmaEnum);

        // Gerar o relatório PDF
        List<Map<String, Object>> data = alunos.stream().map(aluno -> {
            Map<String, Object> map = new HashMap<>();
            map.put("nome", aluno.getNome());
            map.put("turma", aluno.getTurmasEnum() != null ? aluno.getTurmasEnum().toString() : "Não Informada");
            map.put("telefone", aluno.getTelefone());
            map.put("transporteEscolar", aluno.getTransporteEscolar());

            // Ajuste da conversão de responsáveis
            String responsaveisFormatados = aluno.getAdultosResponsaveis() != null && !aluno.getAdultosResponsaveis().isEmpty()
                    ? aluno.getAdultosResponsaveis().stream()
                    .map(responsavel -> responsavel.getNome() + " (" + responsavel.getGrauParentesco().name() + ")")
                    .collect(Collectors.joining(", "))
                    : "Nenhum Responsável Cadastrado";

            map.put("responsaveis", responsaveisFormatados);
            return map;
        }).toList();

        Map<String, Object> parameters = Map.of("turma", turmaEnum.toString());

        pdfGenerator.generateReport(data, response.getOutputStream(), parameters);
    }






}
