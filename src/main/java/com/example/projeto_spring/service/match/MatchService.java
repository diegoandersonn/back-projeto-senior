package com.example.projeto_spring.service.match;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.mapper.MatchMapper;
import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.match.validations.MatchValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MatchService {

    @Autowired
    private MatchMapper matchMapper;

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private List<MatchValidator> validators;

    public Match register(RegisterMatchDto dto) {
        validators.forEach(v -> v.validate(dto));
        Match match = matchMapper.toEntity(dto);

        Team team = teamRepository.getReferenceById(dto.teamId());
        match.setTeam(team);

        matchRepository.save(match);
        return match;
    }

    public List<Match> list() {
        return matchRepository.findAll();
    }

    public List<Match> listByTeamId(UUID teamId) {
        return matchRepository.findMatchByTeamId(teamId);
    }
}
