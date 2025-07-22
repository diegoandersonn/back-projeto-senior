package com.example.projeto_spring.dto.time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record DtoCadastroTime(
        @NotBlank
        String nome,
        @NotBlank
        String estadio,
        @NotNull
        @PositiveOrZero
        Double saldoTransferencias,
        @NotNull
        UUID nacionalidadeId,
        @NotNull
        UUID usuarioId) {
}
