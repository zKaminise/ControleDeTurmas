package com.example.ControleTurmas.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.text.Normalizer;

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

    private final String descricao;

    GrauParentesco(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static GrauParentesco fromDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Grau de Parentesco não pode estar vazio");
        }

        String normalizedDescricao = Normalizer
                .normalize(descricao, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") // Remove acentos
                .toLowerCase(); // Torna case-insensitive

        for (GrauParentesco grau : GrauParentesco.values()) {
            if (grau.name().equalsIgnoreCase(descricao)) {
                return grau;
            }

            String normalizedGrau = Normalizer
                    .normalize(grau.descricao, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .toLowerCase();

            if (normalizedGrau.equals(normalizedDescricao)) {
                return grau;
            }
        }

        throw new IllegalArgumentException("Valor inválido para Grau de Parentesco: " + descricao);
    }
}
