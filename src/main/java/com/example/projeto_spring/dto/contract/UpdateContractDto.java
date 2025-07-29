package com.example.projeto_spring.dto.contract;

import com.example.projeto_spring.enums.ContractType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record UpdateContractDto(
        @PositiveOrZero(message = "Salário deve ser positivo ou zero.")
        Double salary,
        @Positive(message = "Salário deve ser positivo.")
        Double currentValue,
        ContractType contractType,
        @Future(message = "Data de fim do contrato deve ser no futuro!.")
        LocalDate contractEnd
) {
}
