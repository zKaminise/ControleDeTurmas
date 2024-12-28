package com.example.ControleTurmas.Repositorys;

import com.example.ControleTurmas.Entity.Alunos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Alunos, Long> {
}
