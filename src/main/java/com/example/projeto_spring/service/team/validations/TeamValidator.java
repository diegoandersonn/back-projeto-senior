package com.example.projeto_spring.service.team.validations;

import com.example.projeto_spring.dto.team.RegisterTeamDto;

public interface TeamValidator {
    void validate(RegisterTeamDto teamRegisterDto);
}
