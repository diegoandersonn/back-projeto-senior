package com.example.projeto_spring.dto.team;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;

import java.util.UUID;

public record TeamListDto(UUID id, String name, String stadium, Double transferBalance,
                          Nationality nationality) {
    public TeamListDto(Team team) {
        this(team.getId(), team.getName(), team.getStadium(), team.getTransferBalance(), team.getNationality());
    }
}
