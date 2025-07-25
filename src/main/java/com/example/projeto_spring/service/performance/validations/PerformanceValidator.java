package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;

public interface PerformanceValidator {
    void validate(RegisterPerformanceDto dto);
}
