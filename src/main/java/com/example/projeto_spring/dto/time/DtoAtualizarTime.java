package com.example.projeto_spring.dto.time;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DtoAtualizarTime(
        @NotNull Long id,
        String nome,
        String estadio,
        @PositiveOrZero
        Double saldoTransferencias
) {}
