package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.TransferRegisterDto;

public interface TransferValidator {
    void validate(TransferRegisterDto transferRegisterDto);
}
