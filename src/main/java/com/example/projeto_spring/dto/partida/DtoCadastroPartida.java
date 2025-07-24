package com.example.projeto_spring.dto.partida;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.UUID;

public record DtoCadastroPartida(
        @NotNull
        UUID timeId,
        @NotNull
        @PastOrPresent
        LocalDate data
) {
}
