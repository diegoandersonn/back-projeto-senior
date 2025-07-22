package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.domain.Contrato;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.enums.Posicao;

import java.time.LocalDate;
import java.util.UUID;

public record DtoListagemJogador(UUID id, String nome, String nomeCompleto, Time time, Nacionalidade nacionalidade,
                                 int numeroCamisa, Double altura, Posicao posicao, LocalDate dataNascimento, Contrato contrato) {
    public DtoListagemJogador(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getNomeCompleto(),
                jogador.getTime(),
                jogador.getNacionalidade(),
                jogador.getNumeroCamisa(),
                jogador.getAltura(),
                jogador.getPosicao(),
                jogador.getDataNascimento(),
                jogador.getContrato()
        );
    }
}
