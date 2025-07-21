package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.dto.contrato.DtoAtualizarContrato;
import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;
import java.util.UUID;

public record DtoAtualizarJogador(
        @NotNull
        UUID id,
        String nome,
        Integer numeroCamisa,
        Double altura,
        Posicao posicao,
        UUID nacionalidadeId,
        DtoAtualizarContrato contrato
) {}
