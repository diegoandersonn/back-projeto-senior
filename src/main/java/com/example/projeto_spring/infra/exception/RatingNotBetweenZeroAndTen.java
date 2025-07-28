package com.example.projeto_spring.infra.exception;

public class RatingNotBetweenZeroAndTen extends RuntimeException {
    public RatingNotBetweenZeroAndTen(String message) {
        super(message);
    }
}
