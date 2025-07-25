package com.example.projeto_spring.service.match.validations;

import com.example.projeto_spring.dto.match.RegisterMatchDto;

public interface MatchValidator {
    void validate(RegisterMatchDto dto);
}
