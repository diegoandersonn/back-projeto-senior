package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.enums.TransferType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterTransferDto(
        @NotNull(message = "Jogador deve ser informado.")
        UUID playerId,
        @NotNull(message = "Time deve ser informado.")
        UUID teamId,
        @NotNull(message = "Valor deve ser informado.")
        @PositiveOrZero(message = "Valor deve ser positivo ou zero.")
        Double value,
        @NotNull(message = "Data deve ser informada.")
        @PastOrPresent(message = "Data não pode ser futura.")
        LocalDate date,
        @NotNull(message = "Tipo de transferência deve ser informado.")
        TransferType transferType
) {
}
