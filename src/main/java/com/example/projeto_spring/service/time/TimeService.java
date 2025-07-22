package com.example.projeto_spring.service.time;

import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.domain.Usuario;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    public Time cadastrar(DtoCadastroTime dto) {
        Time time = toEntity(dto);
        timeRepository.save(time);
        return time;
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

