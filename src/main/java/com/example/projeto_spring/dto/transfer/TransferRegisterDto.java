package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.enums.TransferType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.UUID;

public record TransferRegisterDto(
        @NotNull
        UUID playerId,
        @NotNull
        UUID teamId,
        @NotNull
        @Positive
        Double value,
        @NotNull
        LocalDate date,
        @NotNull
        TransferType transferType
) {
}
