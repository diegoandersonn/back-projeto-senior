package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.Date;

public record DtoCadastroJogador(
        @NotBlank
        String nome,
        @NotBlank
        String nomeCompleto,
        @NotNull
        int numeroCamisa,
        @NotNull
        Double altura,
        @NotNull
        Posicao posicao,
        @NotNull
        Long timeId,
        @NotNull
        Long nacionalidadeId,
        @NotNull
        Double salario,
        @NotNull
        Double valorAtual,
        @NotNull
        Double valorPago,
        @NotNull
        TipoContrato tipoContrato,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @NotNull
        LocalDate contratoInicio,
        @NotNull
        @Future
        LocalDate contratoFim
        ) {
}
