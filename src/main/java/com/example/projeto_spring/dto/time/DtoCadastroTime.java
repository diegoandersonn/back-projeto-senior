package com.example.projeto_spring.dto.time;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DtoCadastroTime(
        @NotBlank
        String nome,
        @NotBlank
        String estadio,
        @NotNull
        @Positive
        Double saldoTransferencias,
        @NotNull
        Long nacionalidadeId) {
}
