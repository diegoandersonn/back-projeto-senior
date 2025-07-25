package com.example.projeto_spring.dto.performance;

import com.example.projeto_spring.domain.Performance;

import java.util.UUID;

public record DetailPerformanceDto(UUID id, UUID match, UUID player, Double rating) {
    public DetailPerformanceDto(Performance performance) {
        this(performance.getId(), performance.getMatch().getId(), performance.getPlayer().getId(), performance.getRating());
    }
}
