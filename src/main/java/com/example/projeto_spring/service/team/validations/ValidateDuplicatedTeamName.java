package com.example.projeto_spring.service.team.validations;

import com.example.projeto_spring.dto.team.RegisterTeamDto;
import com.example.projeto_spring.infra.exception.DuplicatedTeamNameException;
import com.example.projeto_spring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDuplicatedTeamName implements TeamValidator {

    @Autowired
    TeamRepository teamRepository;

    @Override
    public void validate(RegisterTeamDto teamRegisterDto) {
        if (teamRepository.existsByName(teamRegisterDto.name())) {
            throw new DuplicatedTeamNameException(teamRegisterDto.name());
        }
    }
}
