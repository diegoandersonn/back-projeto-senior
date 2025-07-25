package com.example.projeto_spring.dto.player;

import com.example.projeto_spring.dto.contract.UpdateContractDto;
import com.example.projeto_spring.enums.Position;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record UpdatePlayerDto(
        String name,
        @Positive
        Integer shirtNumber,
        @Positive
        Double height,
        Position position,
        UUID nationalityId,
        @Valid
        UpdateContractDto contract
) {
}
