package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.repository.PlayerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatePlayerDoesNotExist implements  TransferValidator{

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public void validate(RegisterTransferDto transferRegisterDto) {
        if (!playerRepository.existsById(transferRegisterDto.playerId())) {
            throw new EntityNotFoundException("Jogador não encontrado");
        }
    }
}
