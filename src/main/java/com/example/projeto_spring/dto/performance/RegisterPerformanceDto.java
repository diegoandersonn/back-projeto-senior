package com.example.projeto_spring.dto.performance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record RegisterPerformanceDto(
        @NotNull
        UUID matchId,
        @NotNull
        UUID playerId,
        @NotNull
        @PositiveOrZero
        Double rating
) {
}
