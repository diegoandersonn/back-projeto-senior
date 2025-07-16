package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DtoCadastroJogador(
        @NotBlank
        String nome,
        @NotBlank
        String nomeCompleto,
        @NotNull
        @Positive
        int numeroCamisa,
        @NotNull
        @Positive
        Double altura,
        @NotNull
        Posicao posicao,
        @NotNull
        Long timeId,
        @NotNull
        Long nacionalidadeId,
        @NotNull
        @Positive
        Double salario,
        @NotNull
        @Positive
        Double valorAtual,
        @NotNull
        @Positive
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
