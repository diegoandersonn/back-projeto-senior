package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.domain.Player;

import java.util.UUID;

public record DetailPlayerDto(UUID id, String name, UUID teamId, String teamName, UUID nationalityId, String nationalityName) {
    public DetailPlayerDto(Player player) {
        this(player.getId(), player.getName(), player.getTeam().getId(), player.getTeam().getName(), player.getNationality().getId(), player.getNationality().getName());
    }
}
