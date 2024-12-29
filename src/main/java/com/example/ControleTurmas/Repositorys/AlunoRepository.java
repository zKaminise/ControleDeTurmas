package com.example.ControleTurmas.Repositorys;

import com.example.ControleTurmas.Entity.Alunos;
import com.example.ControleTurmas.Enums.TurmasEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Alunos, Long> {

    @Query("SELECT a FROM Alunos a WHERE a.turmasEnum = :turma ORDER BY a.nome ASC")
    List<Alunos> findAlunosByTurma(@Param("turma")TurmasEnum turma);

//    @Query("SELECT COUNT(a) > 0 FROM Alunos a WHERE LOWER(UNACCENT(a.nome)) = LOWER(UNACCENT(:nome))")
//    boolean existsByNomeIgnoreCaseUnaccent(@Param("nome") String nome);

    @Query("SELECT COUNT(a) > 0 FROM Alunos a WHERE LOWER(a.nome) = LOWER(:nome)")
    boolean existsByNomeIgnoreCase(@Param("nome") String nome);
}
