package com.example.projeto_spring.infra.exception;

public class MatchOnSameDateException extends RuntimeException {
    public MatchOnSameDateException(String message) {
        super(message);
    }
}
