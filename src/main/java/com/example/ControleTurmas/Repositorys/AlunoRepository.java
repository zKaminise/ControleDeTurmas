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
}
