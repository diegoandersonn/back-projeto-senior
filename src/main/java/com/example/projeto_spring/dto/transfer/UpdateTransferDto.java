package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.enums.TransferType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record UpdateTransferDto(
        @PositiveOrZero
        Double value,
        @PastOrPresent
        LocalDate date,
        TransferType transferType) {
}
