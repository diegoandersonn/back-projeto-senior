package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.infra.exception.DuplicatedTransferException;
import com.example.projeto_spring.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateDuplicatedTransfer implements TransferValidator {

    @Autowired
    private TransferRepository transferRepository;

    @Override
    public void validate(RegisterTransferDto transferRegisterDto) {
        if (transferRepository.existsByPlayerIdAndDate(transferRegisterDto.playerId(), transferRegisterDto.date())) {
            throw new DuplicatedTransferException("Transferencia duplicada");
        }
    }
}
