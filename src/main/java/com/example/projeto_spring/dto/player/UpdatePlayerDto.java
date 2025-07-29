package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.dto.contract.UpdateContractDto;
import com.example.projeto_spring.enums.Position;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdatePlayerDto(
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,
        @Positive(message = "O número da camisa deve ser positivo")
        Integer shirtNumber,
        @Positive(message = "A altura deve ser positiva")
        Double height,
        Position position,
        UUID nationalityId,
        @Valid
        UpdateContractDto contract
) {
}
