package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import com.example.projeto_spring.domain.time.Time;

public record DtoDetalhamentoJogador(Long id, String nome, Long timeId, Long nacionalidadeId) {
    public DtoDetalhamentoJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTimeId(), jogador.getNacionalidadeId());
    }
}
