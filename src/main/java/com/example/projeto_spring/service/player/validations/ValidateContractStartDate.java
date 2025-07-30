package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.infra.exception.InvalidTransferWindowException;
import com.example.projeto_spring.repository.NationalityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateContractStartDate implements PlayerValidator {

    @Autowired
    private NationalityRepository nationalityRepository;

    public void validate(RegisterPlayerDto dto) {
        Nationality nationality = nationalityRepository.getReferenceById(dto.nationalityId());
        var contract = dto.contract().contractStart();
        int year = contract.getYear();
        Boolean firstWindow, secondWindow;

        if (dto.contract().paidValue() == 0.00) {
            return;
        }

        if (nationality.getContinent().equals("Americas")) {
            firstWindow = !contract.isBefore(LocalDate.of(year, 1, 1)) && !contract.isAfter(LocalDate.of(year, 4, 16));
            secondWindow = !contract.isBefore(LocalDate.of(year, 6, 22)) && !contract.isAfter(LocalDate.of(year, 9, 2));
        } else if (nationality.getContinent().equals("Europe")) {
            firstWindow = !contract.isBefore(LocalDate.of(year, 1, 1)) && !contract.isAfter(LocalDate.of(year, 2, 2));
            secondWindow = !contract.isBefore(LocalDate.of(year, 7, 1)) && !contract.isAfter(LocalDate.of(year, 8, 31));
        } else {
            firstWindow = !contract.isBefore(LocalDate.of(year, 1, 1)) && !contract.isAfter(LocalDate.of(year, 1, 31));
            secondWindow = !contract.isBefore(LocalDate.of(year, 7, 1)) && !contract.isAfter(LocalDate.of(year, 8, 31));
        }

        if (!firstWindow && !secondWindow) {
            throw new InvalidTransferWindowException("Data fora das janela de transferência");
        }
    }
}
