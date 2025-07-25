package com.example.projeto_spring.dto.performance;


import jakarta.validation.constraints.PositiveOrZero;

public record UpdatePerformanceDto(
        @PositiveOrZero
        Double rating
        ) {
}
