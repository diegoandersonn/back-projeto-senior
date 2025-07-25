package com.example.projeto_spring.service.team.validations;

import com.example.projeto_spring.dto.team.TeamRegisterDto;

public interface TeamValidator {
    void validate(TeamRegisterDto teamRegisterDto);
}
