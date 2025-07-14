package com.example.projeto_spring.domain.jogador;

import java.time.LocalDate;

public record DtoListarJogador(Long id, String nome, String nomeCompleto, Long timeId, Long nacionalidadeId,
                               int numeroCamisa, Double altura, Posicao posicao, Double valorAtual,
                               Double valorPago, Double salario, TipoContrato tipoContrato, LocalDate dataNascimento,
                               LocalDate contratoInicio, LocalDate contratoFim) {
    public DtoListarJogador(Jogador jogador) {
        this(
                jogador.getId(),
                jogador.getNome(),
                jogador.getNomeCompleto(),
                jogador.getTimeId(),
                jogador.getNacionalidadeId(),
                jogador.getNumeroCamisa(),
                jogador.getAltura(),
                jogador.getPosicao(),
                jogador.getValorAtual(),
                jogador.getValorPago(),
                jogador.getSalario(),
                jogador.getTipoContrato(),
                jogador.getDataNascimento(),
                jogador.getContratoInicio(),
                jogador.getContratoFim()
        );
    }
}
