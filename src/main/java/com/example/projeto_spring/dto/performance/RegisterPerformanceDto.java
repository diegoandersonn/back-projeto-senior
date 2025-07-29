package com.example.projeto_spring.dto.performance;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record RegisterPerformanceDto(
        @NotNull(message = "Partida deve ser informada.")
        UUID matchId,
        @NotNull(message = "Jogador deve ser informado.")
        UUID playerId,
        @NotNull(message = "Nota deve ser informada.")
        @PositiveOrZero(message = "Nota deve ser positiva ou zero")
        Double rating
) {
}
