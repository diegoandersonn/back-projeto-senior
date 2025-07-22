package com.example.projeto_spring.service.jogador.validacoes;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaNumeroCamisa implements ValidadorJogador {

    @Autowired
    JogadorRepository jogadorRepository;

    public void validar(DtoCadastroJogador dto) {
        if (jogadorRepository.existsByTimeIdAndNumeroCamisa(dto.timeId(), dto.numeroCamisa())) {
            Jogador jogadorExistente = jogadorRepository.findByTimeIdAndNumeroCamisa(dto.timeId(), dto.numeroCamisa());
            jogadorExistente.setNumeroCamisa(0);
        }

        if (dto.numeroCamisa() < 1 || dto.numeroCamisa() > 99) {
            throw new IllegalArgumentException("Número da camisa deve estar entre 1 e 99.");
        }
    }
}
