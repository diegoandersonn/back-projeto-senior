package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.domain.Jogador;

import java.util.UUID;

public record DtoDetalhamentoJogador(UUID id, String nome, UUID timeId, UUID nacionalidadeId) {
    public DtoDetalhamentoJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTimeId(), jogador.getNacionalidadeId());
    }
}
