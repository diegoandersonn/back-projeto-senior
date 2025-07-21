package com.example.projeto_spring.dto.contrato;

import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.UUID;

public record DtoAtualizarContrato(
        @NotNull
        UUID id,
        @PositiveOrZero
        Double salario,
        @Positive
        Double valorAtual,
        TipoContrato tipoContrato,
        @Future
        LocalDate contratoFim
) {
}
