package com.example.projeto_spring.dto.transfer;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.enums.TransferType;

import java.time.LocalDate;
import java.util.UUID;

public record ListTransferDto(UUID id, Team team, Player player, Double value, LocalDate date, TransferType transferType) {
    public ListTransferDto(Transfer transfer) {
        this(transfer.getId(), transfer.getTeam(), transfer.getPlayer(), transfer.getValue(), transfer.getDate(), transfer.getTransferType());
    }
}
