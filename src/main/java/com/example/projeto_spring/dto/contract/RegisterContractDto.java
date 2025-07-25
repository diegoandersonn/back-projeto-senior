package com.example.projeto_spring.dto.contract;

import com.example.projeto_spring.enums.ContractType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterContractDto(
        @NotNull
        @Positive
        Double salary,
        @NotNull
        @Positive
        Double currentValue,
        @NotNull
        @PositiveOrZero
        Double paidValue,
        @NotNull
        ContractType contractType,
        @NotNull
        LocalDate contractStart,
        @NotNull
        @Future
        LocalDate contractEnd
) {
}
