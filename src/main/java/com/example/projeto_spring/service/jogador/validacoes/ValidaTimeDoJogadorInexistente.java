package com.example.projeto_spring.service.jogador.validacoes;

import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.infra.exception.ValidacaoException;
import com.example.projeto_spring.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTimeDoJogadorInexistente implements ValidadorJogador {

    @Autowired
    private TimeRepository timeRepository;

    public void validar(DtoCadastroJogador dto) {
        if (!timeRepository.existsById(dto.timeId())) {
            throw new ValidacaoException("Time não encontrando");
        }
    }
}
