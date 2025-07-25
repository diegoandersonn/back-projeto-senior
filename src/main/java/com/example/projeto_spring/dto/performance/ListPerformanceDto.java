package com.example.projeto_spring.dto.performance;

import com.example.projeto_spring.domain.Performance;

import java.util.UUID;

public record ListPerformanceDto(UUID id, UUID partidaId, UUID jogadorId, Double nota) {
    public ListPerformanceDto(Performance performance) {
        this(performance.getId(), performance.getMatch().getId(), performance.getPlayer().getId(), performance.getRating());
    }
}
