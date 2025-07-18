package com.example.projeto_spring.infra.exception;

public class ValidacaoExpection extends RuntimeException {
    public ValidacaoExpection(String s) {
        super(s);
    }
}
