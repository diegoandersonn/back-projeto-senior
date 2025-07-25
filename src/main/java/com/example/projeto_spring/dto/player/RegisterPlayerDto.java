package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.enums.Position;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterPlayerDto(
        @NotBlank
        String name,
        @NotBlank
        String fullName,
        @NotNull
        @Positive
        int shirtNumber,
        @NotNull
        @Positive
        Double height,
        @NotNull
        Position position,
        @NotNull
        UUID teamId,
        @NotNull
        UUID nationalityId,
        @NotNull
        @Past
        LocalDate birthDate,
        @Valid
        RegisterContractDto contract
) {
}
