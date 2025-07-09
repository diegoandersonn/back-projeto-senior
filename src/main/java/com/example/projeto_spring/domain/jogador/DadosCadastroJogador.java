package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;

public record DadosCadastroJogador(
        @NotBlank
        String nome,
        @NotNull
        int numeroCamisa,
        @NotNull
        Long timeId,
        @NotNull
        Nacionalidade nacionalidade,
        @NotNull
        Double valorAtual,
        @NotNull
        Double valorPago,
        @NotNull
        TipoContrato tipoContrato,
        @NotNull
        @Past
        LocalDateTime dataNascimento,
        @NotNull
        LocalDateTime contratoInicio,
        @NotNull
        @Future
        LocalDateTime contratoFim
        ) {
}
