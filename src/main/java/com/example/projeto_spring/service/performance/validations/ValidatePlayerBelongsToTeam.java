package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.infra.exception.PlayerNotInMatchTeamException;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePlayerBelongsToTeam implements PerformanceValidator {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public void validate(RegisterPerformanceDto dto) {
        var player = playerRepository.getReferenceById(dto.playerId());
        var teamId = player.getTeam().getId();
        if (!matchRepository.existsByTeamIdAndId(teamId, dto.matchId())) {
            throw new PlayerNotInMatchTeamException("Jogador não pode player uma match que não é do seu time");
        }
    }
}
