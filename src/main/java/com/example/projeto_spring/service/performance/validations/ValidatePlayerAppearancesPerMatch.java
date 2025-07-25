package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.repository.PerformanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePlayerAppearancesPerMatch implements PerformanceValidator {

    @Autowired
    private PerformanceRepository performanceRepository;

    public void validate(RegisterPerformanceDto dto) {
        if (performanceRepository.existsByPlayerIdAndMatchId(dto.playerId(), dto.matchId())) {
            throw new IllegalArgumentException("Jogador não pode ter duas atuações por match");
        }
    }
}
