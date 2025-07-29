package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.enums.Position;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record RegisterPlayerDto(
        @NotBlank(message = "Nome deve ser informado.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,
        @NotBlank(message = "Nome completo deve ser informado.")
        @Size(min = 3, max = 255, message = "O nome deve ter entre 3 e 255 caracteres.")
        String fullName,
        @NotNull(message = "Número da camisa deve ser informado.")
        @Positive(message = "O número da camisa deve ser positivo")
        int shirtNumber,
        @NotNull(message = "Altura deve ser informada.")
        @Positive(message = "A altura deve ser positiva")
        Double height,
        @NotNull(message = "Posição deve ser informada.")
        Position position,
        @NotNull(message = "Time deve ser informado.")
        UUID teamId,
        @NotNull(message = "Nacionalidade deve ser informada.")
        UUID nationalityId,
        @NotNull(message = "Data de nascimento deve ser informada.")
        @Past(message = "Data de nascimento deve ser no passado")
        LocalDate birthDate,
        @Valid
        RegisterContractDto contract
) {
}
