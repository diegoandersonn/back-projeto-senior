package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.enums.TransferType;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateTransferDto(
        @Positive(message = "Valor deve ser positivo.")
        Double value,
        @PastOrPresent(message = "Data não pode ser futura.")
        LocalDate date,
        @Pattern(regexp = "SALE|LOAN|PURCHASE", message = "Tipo de transferência inválido.")
        TransferType transferType) {
}
