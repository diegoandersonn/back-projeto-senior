package com.example.projeto_spring.dto.transferencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.UUID;

public record DtoCadastroTransferencia(
        @NotNull
        UUID jogadorId,
        @NotNull
        UUID timeId,
        @NotNull
        @Positive
        Double valor,
        @NotNull
        LocalDate data
) {
}
