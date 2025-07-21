package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.domain.Contrato;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;

import java.time.LocalDate;
import java.util.UUID;

public record DtoListarJogador(UUID id, String nome, String nomeCompleto, UUID timeId, UUID nacionalidadeId,
                               int numeroCamisa, Double altura, Posicao posicao, LocalDate dataNascimento, Contrato contrato) {
    public DtoListarJogador(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getNomeCompleto(),
                jogador.getTimeId(),
                jogador.getNacionalidadeId(),
                jogador.getNumeroCamisa(),
                jogador.getAltura(),
                jogador.getPosicao(),
                jogador.getDataNascimento(),
                jogador.getContrato()
        );
    }
}
