package com.example.ControleTurmas.Entity;

import com.example.ControleTurmas.Enums.GrauParentesco;
import com.example.ControleTurmas.Enums.TurmasEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_alunos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alunos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 3, message = "Nome não pode estar vazio")
    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "turma", nullable = false)
    private TurmasEnum turmasEnum;

    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números.")
    @Column(length = 11, nullable = false)
    private String telefone;

    @Column(name = "transporte_escolar", nullable = false)
    private String transporteEscolar;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<AdultoResponsavel> adultosResponsaveis = new ArrayList<>(); // Inicializar a lista

    public List<AdultoResponsavel> getAdultosResponsaveis() {
        return adultosResponsaveis;
    }

    public void setAdultosResponsaveis(List<AdultoResponsavel> adultosResponsaveis) {
        this.adultosResponsaveis = adultosResponsaveis;
    }

    public String getTransporteEscolar() {
        return transporteEscolar;
    }

    public void setTransporteEscolar(String transporteEscolar) {
        this.transporteEscolar = transporteEscolar;
    }

    public @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números.") String getTelefone() {
        return telefone;
    }

    public void setTelefone(@Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter apenas números.") String telefone) {
        this.telefone = telefone;
    }

    public TurmasEnum getTurmasEnum() {
        return turmasEnum;
    }

    public void setTurmasEnum(TurmasEnum turmasEnum) {
        this.turmasEnum = turmasEnum;
    }

    public @Length(min = 3, message = "Nome não pode estar vazio") String getNome() {
        return nome;
    }

    public void setNome(@Length(min = 3, message = "Nome não pode estar vazio") String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Alunos{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", turmasEnum=" + turmasEnum +
                ", telefone='" + telefone + '\'' +
                ", transporteEscolar='" + transporteEscolar + '\'' +
                '}';
    }
}
