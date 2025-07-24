package com.example.projeto_spring.service.transferencia;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.mapper.TransferenciaMapper;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaMapper transferenciaMapper;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    public Transferencia compra(DtoCadastroTransferencia dto) {
        Transferencia transferencia = transferenciaMapper.toEntity(dto);

        Jogador jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        transferencia.setJogador(jogador);

        Time time = timeRepository.getReferenceById(dto.timeId());
        transferencia.setTime(time);

        transferenciaRepository.save(transferencia);
        return transferencia;
    }

    public Transferencia venda(DtoCadastroTransferencia dto) {
        Transferencia transferencia = transferenciaMapper.toEntity(dto);

        Jogador jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        transferencia.setJogador(jogador);

        Time time = timeRepository.getReferenceById(dto.timeId());
        transferencia.setTime(time);

        transferenciaRepository.save(transferencia);
        return transferencia;
    }
}
