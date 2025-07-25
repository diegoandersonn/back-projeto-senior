package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.enums.TransferType;

import java.time.LocalDate;
import java.util.UUID;

public record TransferListDto(UUID id, UUID teamId, UUID playerId, Double value, LocalDate date, TransferType transferType) {
    public TransferListDto(Transfer transfer) {
        this(transfer.getId(), transfer.getTeam().getId(), transfer.getPlayer().getId(), transfer.getValue(), transfer.getDate(), transfer.getTransferType());
    }
}
