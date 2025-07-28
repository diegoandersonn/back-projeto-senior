package com.example.projeto_spring.infra.exception;

public class DuplicatePlayerPerformanceException extends RuntimeException {
    public DuplicatePlayerPerformanceException(String message) {
        super(message);
    }
}
