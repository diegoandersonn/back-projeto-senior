package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.domain.Contrato;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.contrato.DtoListagemContrato;
import com.example.projeto_spring.enums.Posicao;

import java.time.LocalDate;
import java.util.UUID;

public record DtoListagemJogador(UUID id, String nome, String nomeCompleto, UUID timeId, Nacionalidade nacionalidade,
                                 int numeroCamisa, Double altura, Posicao posicao, LocalDate dataNascimento, DtoListagemContrato contrato) {
    public DtoListagemJogador(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getNomeCompleto(),
                jogador.getTime().getId(),
                jogador.getNacionalidade(),
                jogador.getNumeroCamisa(),
                jogador.getAltura(),
                jogador.getPosicao(),
                jogador.getDataNascimento(),
                new DtoListagemContrato(jogador.getContrato())
        );
    }
}
