package com.example.ControleTurmas.Controllers;

import com.example.ControleTurmas.Entity.Alunos;
import com.example.ControleTurmas.Enums.TurmasEnum;
import com.example.ControleTurmas.Repositorys.AlunoRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    public ResponseEntity<Alunos> criarAluno(@RequestBody Alunos alunos) {
        Alunos novoAluno = alunoRepository.save(alunos);
        return ResponseEntity.ok(novoAluno);
    }

    @GetMapping
    public ResponseEntity<List<Alunos>> listarAlunos() {
        List<Alunos> alunos = alunoRepository.findAll().stream().sorted((a1, a2) -> a1.getNome().compareToIgnoreCase(a2.getNome())).toList();
        return ResponseEntity.ok(alunos);
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
}
