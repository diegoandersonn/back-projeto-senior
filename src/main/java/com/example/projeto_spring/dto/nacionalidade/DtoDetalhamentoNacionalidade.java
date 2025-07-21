package com.example.projeto_spring.dto.nacionalidade;

import com.example.projeto_spring.domain.Nacionalidade;

import java.util.UUID;

public record DtoDetalhamentoNacionalidade(UUID id, String nome, String imagemBandeira, String sigla, String continente) {
    public DtoDetalhamentoNacionalidade(Nacionalidade nacionalidade) {
        this(nacionalidade.getId(), nacionalidade.getNome(), nacionalidade.getImagemBandeira(), nacionalidade.getSigla(), nacionalidade.getContinente());
    }
}
