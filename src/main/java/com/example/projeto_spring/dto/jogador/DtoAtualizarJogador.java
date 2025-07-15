package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record DtoAtualizarJogador(
        @NotNull
        Long id,
        String nome,
        Integer numeroCamisa,
        Double altura,
        Posicao posicao,
        Long nacionalidadeId,
        @PositiveOrZero
        Double salario,
        @Positive
        Double valorAtual,
        TipoContrato tipoContrato,
        @Future
        LocalDate contratoFim
) {}
