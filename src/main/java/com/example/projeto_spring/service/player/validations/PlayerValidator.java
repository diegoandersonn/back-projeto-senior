package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.dto.player.RegisterPlayerDto;

public interface PlayerValidator {
    void validate(RegisterPlayerDto dto);
}
