package com.example.projeto_spring.service.match.validations;

import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.infra.exception.MatchOnSameDateException;
import com.example.projeto_spring.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateMatchOnSameDate implements MatchValidator {

    @Autowired
    private MatchRepository matchRepository;

    public void validate(RegisterMatchDto dto) {
        if (matchRepository.existsByDateAndTeamId(dto.date(), dto.timeId())) {
            throw new MatchOnSameDateException("Time não pode ter duas partidas na mesma data");
        }
    }
}
