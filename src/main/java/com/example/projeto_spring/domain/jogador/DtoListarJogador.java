package com.example.projeto_spring.domain.jogador;

import java.time.LocalDate;

public record DtoListarJogador(Long id, String nome, Long timeId, Long nacionalidadeId, int numeroCamisa, Double valorAtual,
                               Double valorPago, TipoContrato tipoContrato, LocalDate dataNascimento,
                               LocalDate contratoInicio, LocalDate contratoFim) {
    public DtoListarJogador(Jogador jogador) {
        this(jogador.getId(), jogador.getNome(), jogador.getTimeId(), jogador.getNacionalidadeId(), jogador.getNumeroCamisa(), jogador.getValorAtual(), jogador.getValorPago(), jogador.getTipoContrato(), jogador.getDataNascimento(), jogador.getContratoFim(), jogador.getContratoInicio());
    }
}
