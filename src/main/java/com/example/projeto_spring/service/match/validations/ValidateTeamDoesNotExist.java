package com.example.projeto_spring.service.match.validations;

import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTeamDoesNotExist implements MatchValidator {

    @Autowired
    private TeamRepository timeRepository;

    public void validate(RegisterMatchDto dto) {
        if (!timeRepository.existsById(dto.timeId())) {
            throw new EntityNotFoundException("Time não encontrando");
        }
    }
}
