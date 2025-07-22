package com.example.projeto_spring.dto.atuacao;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Partida;

import java.util.UUID;
import java.util.function.DoubleToLongFunction;

public record DtoDetalhamentoAtuacao(UUID id, Partida partida, Jogador jogador, Double nota) {
    public DtoDetalhamentoAtuacao(Atuacao atuacao) {
        this(atuacao.getId(), atuacao.getPartida(), atuacao.getJogador(), atuacao.getNota());
    }
}
