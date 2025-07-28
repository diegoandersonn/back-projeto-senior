package com.example.projeto_spring.infra.exception;

public class InvalidTransferWindowException extends RuntimeException {
    public InvalidTransferWindowException(String message) {
        super(message);
    }
}
