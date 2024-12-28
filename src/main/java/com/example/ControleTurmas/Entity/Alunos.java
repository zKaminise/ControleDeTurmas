package com.example.ControleTurmas.Entity;

import com.example.ControleTurmas.Enums.GrauParentesco;
import com.example.ControleTurmas.Enums.TurmasEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tb_alunos")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, message = "Nome não pode estar vazio")
    @Column(nullable = false)
    @Schema(example = "Maria José da Silva",minLength = 3, maxLength = 150, requiredMode = Schema.RequiredMode.REQUIRED, description = "Nome do Aluno")
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "Turma")
    private TurmasEnum turmasEnum;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números.")
    @Column(length = 11, nullable = false)
    @Schema(example = "34999999999",minLength = 10, maxLength = 11, description = "Celular do Responsável")
    private String telefone;

    private String transporteEscolar;

    private String adultosResponsaveis;

    @Enumerated(EnumType.STRING)
    private GrauParentesco grauParentesco;
}
