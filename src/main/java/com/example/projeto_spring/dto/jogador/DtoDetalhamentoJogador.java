package com.example.projeto_spring.dto.jogador;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;

import java.util.UUID;

public record DtoDetalhamentoJogador(UUID id, String nome, UUID timeId, String nomeTime, UUID nacionalidadeId, String nomeNacionalidade) {
    public DtoDetalhamentoJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTime().getId(), jogador.getTime().getNome(), jogador.getNacionalidade().getId(), jogador.getNacionalidade().getNome());
    }
}
