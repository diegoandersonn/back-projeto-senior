package com.example.projeto_spring.dto.match;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterMatchDto(
        @NotNull(message = "Time deve ser informado.")
        UUID timeId,
        @NotNull(message = "Data deve ser informada.")
        @PastOrPresent(message = "Data deve estar no presente ou passado!")
        LocalDate date
) {
}
