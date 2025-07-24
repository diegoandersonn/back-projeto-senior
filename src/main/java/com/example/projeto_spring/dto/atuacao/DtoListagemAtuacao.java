package com.example.projeto_spring.dto.atuacao;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Partida;

import java.util.UUID;

public record DtoListagemAtuacao(UUID id, UUID partidaId, UUID jogadorId, Double nota) {
    public DtoListagemAtuacao(Atuacao atuacao) {
        this(atuacao.getId(), atuacao.getPartida().getId(), atuacao.getJogador().getId(), atuacao.getNota());
    }
}
