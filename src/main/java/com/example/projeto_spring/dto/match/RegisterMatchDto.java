package com.example.projeto_spring.dto.match;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterMatchDto(
        @NotNull
        UUID timeId,
        @NotNull
        @PastOrPresent
        LocalDate date
) {
}
