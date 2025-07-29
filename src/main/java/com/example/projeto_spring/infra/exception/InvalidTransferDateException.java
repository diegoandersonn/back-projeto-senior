package com.example.projeto_spring.infra.exception;

public class InvalidTransferDateException extends RuntimeException {
    public InvalidTransferDateException(String message) {
        super(message);
    }
}
