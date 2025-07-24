package com.example.projeto_spring.service.partida.validacoes;

import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaPartidaNaMesmaData implements ValidadorPartida {

    @Autowired
    private PartidaRepository partidaRepository;

    public void validar(DtoCadastroPartida dto) {
        if (partidaRepository.existsByDataAndTimeId(dto.data(), dto.timeId())) {
            throw new IllegalArgumentException("Time não pode ter duas partidas na mesma data");
        }
    }
}
