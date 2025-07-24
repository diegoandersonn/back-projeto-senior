package com.example.projeto_spring.service.time;

import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.domain.Usuario;
import com.example.projeto_spring.dto.mapper.JogadorMapper;
import com.example.projeto_spring.dto.mapper.TimeMapper;
import com.example.projeto_spring.dto.time.DtoAtualizarTime;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TimeService {

    @Autowired
    private TimeMapper timeMapper;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Time cadastrar(DtoCadastroTime dto) {
        Time time = timeMapper.toEntity(dto);

        Usuario usuario = usuarioRepository.getReferenceById(dto.usuarioId());
        time.setUsuario(usuario);

        Nacionalidade nacionalidade = nacionalidadeRepository.getReferenceById(dto.nacionalidadeId());
        time.setNacionalidade(nacionalidade);

        timeRepository.save(time);
        return time;
    }

    public List<Time> listar() {
        return timeRepository.findAll();
    }

    public Time listarTimePorId(UUID timeId) {
        return timeRepository.getReferenceById(timeId);
    }

    public List<Time> listarTimesPorUsuarios(UUID usuarioId) {
        Optional<List<Time>> timeOpt = timeRepository.findAllByUsuarioId(usuarioId);
        return timeOpt.orElseThrow();
    }

    public Time atualizar(DtoAtualizarTime dto, UUID timeId) {
        Optional<Time> timeOpt = timeRepository.findById(timeId);
        Time time = timeOpt.orElseThrow();

        if (dto.nome() != null) {
            time.setNome(dto.nome());
        }
        if (dto.estadio() != null) {
            time.setEstadio(dto.estadio());
        }
        if (dto.saldoTransferencias() != null) {
            time.setSaldoTransferencias(dto.saldoTransferencias());
        }
        return time;
    }

    public void excluir(UUID id) {
        timeRepository.deleteById(id);
    }

    private Time toEntity(DtoCadastroTime dto) {
        Nacionalidade nacionalidade = nacionalidadeRepository.getReferenceById(dto.nacionalidadeId());
        Usuario usuario = usuarioRepository.getReferenceById(dto.usuarioId());
        return new Time(
                null,
                dto.nome(),
                dto.estadio(),
                dto.saldoTransferencias(),
                usuario,
                nacionalidade
        );
    }

}

