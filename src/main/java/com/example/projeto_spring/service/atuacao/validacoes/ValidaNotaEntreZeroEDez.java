package com.example.projeto_spring.service.atuacao.validacoes;

import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import org.springframework.stereotype.Component;

@Component
public class ValidaNotaEntreZeroEDez implements ValidadorAtuacao {
    public void validar(DtoCadastroAtuacao dto) {
        if (dto.nota() < 0 || dto.nota() > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }
    }
}
