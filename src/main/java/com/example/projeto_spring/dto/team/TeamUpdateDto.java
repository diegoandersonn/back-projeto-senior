package com.example.projeto_spring.dto.team;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record TeamUpdateDto(
        @NotNull UUID id,
        String name,
        String stadium,
        @PositiveOrZero
        Double transferBalance
) {}
