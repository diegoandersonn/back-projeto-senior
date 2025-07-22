package com.example.projeto_spring.service.transferencia;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    public Transferencia cadastar(DtoCadastroTransferencia dto) {
        Transferencia transferencia = toEntity(dto);
        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    private Transferencia toEntity(DtoCadastroTransferencia dto) {
        Jogador jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        Time time = timeRepository.getReferenceById(dto.timeId());
        return new Transferencia(
                null,
                jogador,
                time,
                dto.valor(),
                dto.data()
        );
    }

}
