package com.example.projeto_spring.infra.exception;

public class PlayerNotInMatchTeamException extends RuntimeException {
    public PlayerNotInMatchTeamException(String message) {
        super(message);
    }
}
