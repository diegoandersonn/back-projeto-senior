package com.example.projeto_spring.dto.contract;

import com.example.projeto_spring.enums.ContractType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record UpdateContractDto(
        @PositiveOrZero
        Double salary,
        @Positive
        Double currentValue,
        ContractType contractType,
        @Future
        LocalDate contractEnd
) {
}
