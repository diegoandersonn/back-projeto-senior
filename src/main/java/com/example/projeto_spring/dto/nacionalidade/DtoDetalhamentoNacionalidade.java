package com.example.projeto_spring.dto.nacionalidade;

import com.example.projeto_spring.domain.Nacionalidade;

public record DtoDetalhamentoNacionalidade(Long id, String nome, String imagemBandeira, String sigla, String continente) {
    public DtoDetalhamentoNacionalidade(Nacionalidade nacionalidade) {
        this(nacionalidade.getId(), nacionalidade.getNome(), nacionalidade.getImagemBandeira(), nacionalidade.getSigla(), nacionalidade.getContinente());
    }
}
