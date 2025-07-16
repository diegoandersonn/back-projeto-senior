package com.example.projeto_spring.dto.transferencia;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record DtoCadastroTransferencia(
        @NotNull
        Long jogadorId,
        @NotNull
        Long timeId,
        @NotNull
        @Positive
        Double valor,
        @NotNull
        LocalDate data
) {
}
