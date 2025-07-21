package com.example.projeto_spring.dto.contrato;

import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DtoCadastroContrato(
        @NotNull
        @Positive
        Double salario,
        @NotNull
        @Positive
        Double valorAtual,
        @NotNull
        @PositiveOrZero
        Double valorPago,
        @NotNull
        TipoContrato tipoContrato,
        @NotNull
        LocalDate contratoInicio,
        @NotNull
        @Future
        LocalDate contratoFim
) {
}
