package com.example.projeto_spring.domain.nacionalidade;

public record DtoDetalhamentoNacionalidade(Long id, String nome, String imagemBandeira, String sigla) {
    public DtoDetalhamentoNacionalidade(Nacionalidade nacionalidade) {
        this(nacionalidade.getId(), nacionalidade.getNome(), nacionalidade.getImagemBandeira(), nacionalidade.getSigla());
    }
}
