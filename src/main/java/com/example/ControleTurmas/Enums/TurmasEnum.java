package com.example.ControleTurmas.Enums;

import lombok.Getter;

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

    private final String nome;

    TurmasEnum(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
