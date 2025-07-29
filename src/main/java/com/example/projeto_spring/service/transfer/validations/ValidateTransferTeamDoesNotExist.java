package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.player.validations.PlayerValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTransferTeamDoesNotExist implements PlayerValidator {

    @Autowired
    private TeamRepository teamRepository;

    public void validate(RegisterPlayerDto dto) {
        if (!teamRepository.existsById(dto.teamId())) {
            throw new EntityNotFoundException("Time não encontrando");
        }
    }
}
