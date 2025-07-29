package com.example.projeto_spring.dto.team;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record RegisterTeamDto(
        @NotBlank(message = "Nome do time deve ser informado.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String name,
        @NotBlank(message = "Estádio deve ser informado.")
        @Size(min = 3, max = 100, message = "O estádio deve ter entre 3 e 100 caracteres.")
        String stadium,
        @NotNull(message = "Saldo de transferencias deve ser informado.")
        @PositiveOrZero(message = "Saldo de transferencias deve ser positivo")
        Double transferBalance,
        @NotNull(message = "Nacionalidade deve ser informada.")
        UUID nationalityId,
        @NotNull(message = "Usuario deve ser informado.")
        UUID userId) {
}
