package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.dto.contract.ListContractDto;
import com.example.projeto_spring.enums.Position;

import java.time.LocalDate;
import java.util.UUID;

public record ListPlayerDto(UUID id, String name, String fullName, UUID teamId, Nationality nationality,
                            int shirtNumber, Double height, Position position, LocalDate birthDate, ListContractDto contract) {
    public ListPlayerDto(Player player) {
        this(
                player.getId(),
                player.getName(),
                player.getFullName(),
                player.getTeam().getId(),
                player.getNationality(),
                player.getShirtNumber(),
                player.getHeight(),
                player.getPosition(),
                player.getBirthDate(),
                new ListContractDto(player.getContract())
        );
    }
}
