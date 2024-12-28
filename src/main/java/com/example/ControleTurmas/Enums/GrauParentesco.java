package com.example.ControleTurmas.Enums;

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

    @Override
    public String toString() {
        return grauParentesco;
    }
}
