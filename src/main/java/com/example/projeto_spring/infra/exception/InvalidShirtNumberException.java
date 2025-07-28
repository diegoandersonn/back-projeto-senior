package com.example.projeto_spring.infra.exception;

public class InvalidShirtNumberException extends RuntimeException {
    public InvalidShirtNumberException(String message) {
        super(message);
    }
}
