package com.example.projeto_spring.dto.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record RegisterTeamDto(
        @NotBlank
        String name,
        @NotBlank
        String stadium,
        @NotNull
        @PositiveOrZero
        Double transferBalance,
        @NotNull
        UUID nationalityId,
        @NotNull
        UUID userId) {
}
