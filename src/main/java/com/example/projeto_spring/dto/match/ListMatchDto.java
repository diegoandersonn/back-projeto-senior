package com.example.projeto_spring.dto.match;

import com.example.projeto_spring.domain.Match;

import java.time.LocalDate;
import java.util.UUID;

public record ListMatchDto(UUID id, UUID timeId, LocalDate date) {
    public ListMatchDto(Match match) {
        this(match.getId(), match.getTeam().getId(), match.getDate());
    }
}
