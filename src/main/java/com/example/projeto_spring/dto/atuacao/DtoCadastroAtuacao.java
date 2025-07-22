package com.example.projeto_spring.dto.atuacao;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.UUID;

public record DtoCadastroAtuacao(
        @NotNull
        UUID partidaId,
        @NotNull
        UUID jogadorId,
        @NotNull
        @PositiveOrZero
        Double nota
) {
}
