package com.example.projeto_spring.dto.time;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record DtoAtualizarTime(
        @NotNull UUID id,
        String nome,
        String estadio,
        @PositiveOrZero
        Double saldoTransferencias
) {}
