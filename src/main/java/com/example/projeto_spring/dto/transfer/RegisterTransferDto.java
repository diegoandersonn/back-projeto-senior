package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.enums.TransferType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTransferDto(
        @NotNull
        UUID playerId,
        @NotNull
        UUID teamId,
        @NotNull
        @PositiveOrZero
        Double value,
        @NotNull
        @PastOrPresent
        LocalDate date,
        @NotNull
        TransferType transferType
) {
}
