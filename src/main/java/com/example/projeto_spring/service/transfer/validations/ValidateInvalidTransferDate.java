package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.infra.exception.InvalidTransferDateException;
import com.example.projeto_spring.infra.exception.InvalidTransferWindowException;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidateInvalidTransferDate implements TransferValidator {

    @Autowired
    private TeamRepository teamRepository;

    @Override
    public void validate(RegisterTransferDto transferRegisterDto) {
        var team = teamRepository.getReferenceById(transferRegisterDto.teamId());
        var nationality = team.getNationality();

        Boolean firstWindow, secondWindow;
        var year = transferRegisterDto.date().getYear();

        if (transferRegisterDto.value() == 0) {
            return;
        }

        if (nationality.getContinent().equals("Americas")) {
            firstWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 1, 1)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 4, 16));
            secondWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 6, 22)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 7, 21));
        } else if (nationality.getContinent().equals("Europe")) {
            firstWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 1, 1)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 2, 2));
            secondWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 7, 1)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 8, 31));
        } else {
            firstWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 1, 1)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 1, 31));
            secondWindow = !transferRegisterDto.date().isBefore(LocalDate.of(year, 7, 1)) && !transferRegisterDto.date().isAfter(LocalDate.of(year, 8, 31));
        }

        if (!firstWindow && !secondWindow) {
            throw new InvalidTransferDateException("Data fora das janela de transferência");
        }
    }
}
