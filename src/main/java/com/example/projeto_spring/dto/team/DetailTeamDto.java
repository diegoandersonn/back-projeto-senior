package com.example.projeto_spring.dto.team;

import com.example.projeto_spring.domain.Team;

import java.util.UUID;

public record DetailTeamDto(UUID id, String name, String stadium, Double transferBalance) {
    public DetailTeamDto(Team team) {
        this(team.getId(), team.getName(), team.getStadium(), team.getTransferBalance());
    }
}
