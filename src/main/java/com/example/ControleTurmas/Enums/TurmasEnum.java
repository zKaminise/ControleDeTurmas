package com.example.ControleTurmas.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

import java.text.Normalizer;

@Getter

public enum TurmasEnum {
    Turma_1A("Turma 1A"),
    Turma_1B("Turma 1B"),
    Turma_1C("Turma 1C"),
    Turma_2A("Turma 2A"),
    Turma_2B("Turma 2B"),
    Turma_2C("Turma 2C"),
    Turma_2D("Turma 2D"),
    Turma_3A("Turma 3A"),
    Turma_3B("Turma 3B"),
    Turma_3C("Turma 3C"),
    Turma_4A("Turma 4A"),
    Turma_4B("Turma 4B"),
    Turma_4C("Turma 4C"),
    Turma_5A("Turma 5A"),
    Turma_5B("Turma 5B"),
    Turma_5C("Turma 5C"),
    Turma_6A("Turma 6A"),
    Turma_6B("Turma 6B"),
    Turma_6C("Turma 6C"),
    Turma_7A("Turma 7A"),
    Turma_7B("Turma 7B"),
    Turma_7C("Turma 7C"),
    Turma_8A("Turma 8A"),
    Turma_8B("Turma 8B"),
    Turma_8C("Turma 8C"),
    Turma_9A("Turma 9A"),
    Turma_9B("Turma 9B"),
    Turma_9C("Turma 9C");

    private final String descricao;

    TurmasEnum(String descricao) {
        this.descricao = descricao;
    }

    @JsonValue
    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static TurmasEnum fromDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser nula ou vazia");
        }

        // Normalizar a entrada
        String normalizedDescricao = Normalizer
                .normalize(descricao, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "") // Remove acentos
                .toLowerCase(); // Torna case-insensitive

        // Comparar com o nome do enum e com a descrição
        for (TurmasEnum turma : TurmasEnum.values()) {
            // Comparar com o nome do enum (e.g., "TURMA_1A")
            if (turma.name().equalsIgnoreCase(descricao)) {
                return turma;
            }

            // Comparar com a descrição (e.g., "Turma 1A")
            String normalizedTurma = Normalizer
                    .normalize(turma.descricao, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "")
                    .toLowerCase();

            if (normalizedTurma.equals(normalizedDescricao)) {
                return turma;
            }
        }

        throw new IllegalArgumentException("Valor inválido para Turmas: " + descricao);
    }

}
