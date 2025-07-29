package com.example.projeto_spring.infra.exception;

public class DuplicatedTeamNameException extends RuntimeException {
    public DuplicatedTeamNameException(String teamName) {
        super(String.format("Já existe um time cadastrado com o nome: %s", teamName));
    }
}
