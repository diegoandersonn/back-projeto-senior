package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.dto.contrato.DtoCadastroContrato;
import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

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
        UUID timeId,
        @NotNull
        UUID nacionalidadeId,
        @NotNull
        @Past
        LocalDate dataNascimento,
        @Valid
        DtoCadastroContrato contrato
) {
}
