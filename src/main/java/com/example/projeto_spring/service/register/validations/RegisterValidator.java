package com.example.projeto_spring.service.register.validations;

import com.example.projeto_spring.dto.auth.AuthDto;

public interface RegisterValidator {
    void validate(AuthDto dto);
}
