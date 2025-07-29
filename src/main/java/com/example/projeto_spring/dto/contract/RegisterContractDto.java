package com.example.projeto_spring.dto.contract;

import com.example.projeto_spring.enums.ContractType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterContractDto(
        @NotNull(message = "Salário deve ser informado!")
        @Positive(message = "Salário deve ser positivo")
        Double salary,
        @NotNull(message = "Valor atual deve ser informado!")
        @Positive(message = "Valor atual deve ser positivo")
        Double currentValue,
        @NotNull(message = "Valor pago deve ser informado!")
        @PositiveOrZero(message = "Valor pago deve ser positivo ou zero")
        Double paidValue,
        @NotNull(message = "Tipo de contrato deve ser informado!")
        ContractType contractType,
        @NotNull(message = "Data de começo do contrato deve ser informada!")
        LocalDate contractStart,
        @NotNull(message = "Data de fim do contrato deve ser informada!")
        @Future(message = "Data de fim do contrato deve ser no futuro!")
        LocalDate contractEnd
) {
}
