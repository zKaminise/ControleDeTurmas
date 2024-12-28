package com.example.ControleTurmas.Enums;

import lombok.Getter;

@Getter

public enum TurmasEnum {
    TURMA_1A("Turma 1A"),
    TURMA_1B("Turma 1B"),
    TURMA_1C("Turma 1C"),
    TURMA_2A("Turma 2A"),
    TURMA_2B("Turma 2B"),
    TURMA_2C("Turma 2C"),
    TURMA_2D("Turma 2D"),
    TURMA_3A("Turma 3A"),
    TURMA_3B("Turma 3B"),
    TURMA_3C("Turma 3C"),
    TURMA_4A("Turma 4A"),
    TURMA_4B("Turma 4B"),
    TURMA_4C("Turma 4C"),
    TURMA_5A("Turma 5A"),
    TURMA_5B("Turma 5B"),
    TURMA_5C("Turma 5C"),
    TURMA_6A("Turma 6A"),
    TURMA_6B("Turma 6B"),
    TURMA_6C("Turma 6C"),
    TURMA_7A("Turma 7A"),
    TURMA_7B("Turma 7B"),
    TURMA_7C("Turma 7C"),
    TURMA_8A("Turma 8A"),
    TURMA_8B("Turma 8B"),
    TURMA_8C("Turma 8C"),
    TURMA_9A("Turma 9A"),
    TURMA_9B("Turma 9B"),
    TURMA_9C("Turma 9C");

    private final String nome;

    TurmasEnum(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
