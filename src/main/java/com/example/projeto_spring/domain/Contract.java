package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.contract.UpdateContractDto;
import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.enums.ContractType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
@AllArgsConstructor
public class Contract {
    private Double salary;
    private Double currentValue;
    private Double paidValue;
    @Enumerated(EnumType.STRING)
    private ContractType contractType;
    private LocalDate contractStart;
    private LocalDate contractEnd;

    public Contract() {
    }

    public Contract(RegisterContractDto dto) {
        this.salary = dto.salary();
        this.currentValue = dto.currentValue();
        this.paidValue = dto.paidValue();
        this.contractType = dto.contractType();
        this.contractStart = dto.contractStart();
        this.contractEnd = dto.contractEnd();
    }

    public void updateContract(UpdateContractDto dto) {
        if (dto.salary() != null) {
            this.salary = dto.salary();
        }
        if (dto.currentValue() != null) {
            this.currentValue = dto.currentValue();
        }
        if (dto.contractType() != null) {
            this.contractType = dto.contractType();
        }
        if (dto.contractEnd() != null) {
            this.contractEnd = dto.contractEnd();
        }
    }
}
