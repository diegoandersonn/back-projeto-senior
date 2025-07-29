package com.example.projeto_spring.infra.exception;

public class DuplicatedTransferException extends RuntimeException {
    public DuplicatedTransferException(String message) {
        super(message);
    }
}
