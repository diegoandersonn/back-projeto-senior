package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateTransferTeamDoesNotExist implements TransferValidator {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void validate(RegisterTransferDto dto) {
        if (!teamRepository.existsById(dto.teamId())) {
            throw new EntityNotFoundException("Time não encontrando");
        }
    }
}
