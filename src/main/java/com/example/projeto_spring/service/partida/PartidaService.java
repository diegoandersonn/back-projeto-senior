package com.example.projeto_spring.service.partida;

import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.repository.PartidaRepository;
import com.example.projeto_spring.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private TimeRepository timeRepository;

    public Partida cadastrar(DtoCadastroPartida dto) {
        Partida partida = toEntity(dto);
        partidaRepository.save(partida);
        return partida;
    }

    private Partida toEntity(DtoCadastroPartida dto) {
        Time time = timeRepository.getReferenceById(dto.timeId());
        return new Partida(dto.id(), time, dto.data());
    }
}
