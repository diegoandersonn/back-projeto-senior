package com.example.projeto_spring.service.partida;

import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.mapper.PartidaMapper;
import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.repository.PartidaRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.service.partida.validacoes.ValidadorPartida;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PartidaService {

    @Autowired
    private PartidaMapper partidaMapper;

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private List<ValidadorPartida> validadores;

    public Partida cadastrar(DtoCadastroPartida dto) {
        validadores.forEach(v -> v.validar(dto));
        Partida partida = partidaMapper.toEntity(dto);

        Time time = timeRepository.getReferenceById(dto.timeId());
        partida.setTime(time);

        partidaRepository.save(partida);
        return partida;
    }

    public List<Partida> listar() {
        return partidaRepository.findAll();
    }

    public List<Partida> listarPorTime(UUID timeId) {
        return partidaRepository.findPartidaByTimeId(timeId);
    }

    private Partida toEntity(DtoCadastroPartida dto) {
        Optional<Time> timeOpt = timeRepository.findById(dto.timeId());
        Time time = timeOpt.orElseThrow();
        return new Partida(null, time, dto.data());
    }
}
