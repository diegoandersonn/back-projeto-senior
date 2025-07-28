package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.enums.TransferType;

import java.time.LocalDate;
import java.util.UUID;

public record DetailTransferDto(UUID id, UUID playerId, String playerName, UUID teamId, String teamName, Double value, LocalDate date, TransferType transferType) {
    public DetailTransferDto(Transfer transfer) {
        this(transfer.getId(), transfer.getPlayer().getId(), transfer.getPlayer().getName(), transfer.getTeam().getId(), transfer.getTeam().getName(), transfer.getValue(), transfer.getDate(), transfer.getTransferType());
    }
}
