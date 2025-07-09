package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import com.example.projeto_spring.domain.time.Time;

public record DadosDetalhamentoJogador(Long id, String nome, Time time, Nacionalidade nacionalidade) {
    public DadosDetalhamentoJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTime(), jogador.getNacionalidade());
    }
}
