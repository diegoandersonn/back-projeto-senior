package com.example.projeto_spring.dto.contract;

import com.example.projeto_spring.domain.Contract;
import com.example.projeto_spring.enums.ContractType;

import java.time.LocalDate;

public record ListContractDto(Double salary, Double currentValue, Double paidValue, ContractType contractType, LocalDate contractStart, LocalDate contractEnd) {
    public ListContractDto(Contract contract) {
        this(contract.getSalary(), contract.getCurrentValue(), contract.getPaidValue(), contract.getContractType(), contract.getContractStart(), contract.getContractEnd());
    }
}

