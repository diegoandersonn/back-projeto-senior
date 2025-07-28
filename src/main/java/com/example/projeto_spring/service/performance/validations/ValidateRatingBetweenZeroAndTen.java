package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.infra.exception.RatingNotBetweenZeroAndTen;
import org.springframework.stereotype.Component;

@Component
public class ValidateRatingBetweenZeroAndTen implements PerformanceValidator {
    public void validate(RegisterPerformanceDto dto) {
        if (dto.rating() < 0 || dto.rating() > 10) {
            throw new RatingNotBetweenZeroAndTen("Nota deve estar entre 0 e 10");
        }
    }
}
