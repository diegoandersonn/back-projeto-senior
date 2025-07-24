package com.example.projeto_spring.service.partida.validacoes;

import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.repository.TimeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaTimeDaPartidaInexistente implements ValidadorPartida {

    @Autowired
    private TimeRepository timeRepository;

    public void validar(DtoCadastroPartida dto) {
        if (!timeRepository.existsById(dto.timeId())) {
            throw new EntityNotFoundException("Time não encontrando");
        }
    }
}
