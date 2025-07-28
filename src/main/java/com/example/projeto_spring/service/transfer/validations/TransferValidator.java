package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;

public interface TransferValidator {
    void validate(RegisterTransferDto transferRegisterDto);
}
