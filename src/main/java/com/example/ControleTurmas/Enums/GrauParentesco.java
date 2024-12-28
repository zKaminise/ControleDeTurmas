package com.example.ControleTurmas.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter

public enum GrauParentesco {
    Pai("Pai"),
    Mae("Mãe"),
    Avo("Avó/Avô"),
    Irmao("Irmão"),
    Irma("Irmã"),
    Tios("Tio/Tia"),
    Primos("Primo/Prima"),
    Outro("Outro");

    private final String grauParentesco;

    GrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    @JsonValue
    public String getDescricao() {
        return grauParentesco;
    }

    @JsonCreator
    public static GrauParentesco fromDescricao(String descricao) {
        for (GrauParentesco grau : GrauParentesco.values()) {
            if (grau.grauParentesco.equalsIgnoreCase(descricao)) {
                return grau;
            }
        }
        throw new IllegalArgumentException("Valor inválido para GrauParentesco: " + descricao);
    }

    @Override
    public String toString() {
        return grauParentesco;
    }
}
