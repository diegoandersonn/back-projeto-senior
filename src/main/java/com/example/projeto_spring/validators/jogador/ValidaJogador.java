package com.example.projeto_spring.validators.jogador;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import com.example.projeto_spring.validators.jogador.validacoes.ValidadorJogador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidaJogador {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private List<ValidadorJogador> validadores;

    public Jogador validar(DtoCadastroJogador dto) {
        validadores.forEach(v -> v.validar(dto));
        Jogador jogador = new Jogador(dto);
        jogadorRepository.save(jogador);
        DtoCadastroTransferencia dtoTransferencia = new DtoCadastroTransferencia(jogador.getId(), jogador.getTimeId(), jogador.getValorPago() == 0 ? 0 : jogador.getValorPago() * -1, jogador.getContratoInicio());
        Transferencia transferencia = new Transferencia(dtoTransferencia);
        transferenciaRepository.save(transferencia);
        return jogador;
    }
}