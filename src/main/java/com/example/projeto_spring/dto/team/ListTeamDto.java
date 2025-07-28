package com.example.projeto_spring.dto.team;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;

import java.util.UUID;

public record ListTeamDto(UUID id, String name, String stadium, Double transferBalance,
                          Nationality nationality) {
    public ListTeamDto(Team team) {
        this(team.getId(), team.getName(), team.getStadium(), team.getTransferBalance(), team.getNationality());
    }
}
