package com.example.projeto_spring.dto.team;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record UpdateTeamDto(
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,
        @Size(min = 3, max = 100, message = "O estádio deve ter entre 3 e 100 caracteres.")
        String stadium,
        @PositiveOrZero(message = "Saldo de transferencias deve ser positivo")
        Double transferBalance
) {
}
