package com.example.projeto_spring.service.performance;

import com.example.projeto_spring.domain.Performance;
import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.dto.mapper.PerformanceMapper;
import com.example.projeto_spring.dto.performance.UpdatePerformanceDto;
import com.example.projeto_spring.repository.PerformanceRepository;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.service.performance.validations.PerformanceValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PerformanceService {

    @Autowired
    private PerformanceMapper performanceMapper;

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private List<PerformanceValidator> validators;

    public Performance register(RegisterPerformanceDto dto) {
        validators.forEach(v -> v.validate(dto));
        Performance performance = performanceMapper.toEntity(dto);

        Match match = matchRepository.getReferenceById(dto.matchId());
        performance.setMatch(match);

        Player player = playerRepository.getReferenceById(dto.playerId());
        performance.setPlayer(player);

        performanceRepository.save(performance);
        return performance;
    }

    public List<Performance> list() {
        return performanceRepository.findAll();
    }

    public List<Performance> listByPlayerId(UUID playerId) {
        return performanceRepository.findByPlayerId(playerId);
    }

    public List<Performance> listByTeamId(UUID teamId) {
        return performanceRepository.findByTeamId(teamId);
    }

    public Performance update(UUID id, UpdatePerformanceDto dto) {
        Performance performance = performanceRepository.getReferenceById(id);

        if (dto.rating() != null) {
            performance.setRating(dto.rating());
        }

        return performance;
    }

    public void delete(UUID id) {
        performanceRepository.deleteById(id);
    }
}
