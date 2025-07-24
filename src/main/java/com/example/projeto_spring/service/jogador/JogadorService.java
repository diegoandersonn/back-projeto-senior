package com.example.projeto_spring.service.jogador;

import com.example.projeto_spring.domain.*;
import com.example.projeto_spring.dto.jogador.DtoAtualizarJogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.dto.mapper.JogadorMapper;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.enums.TipoTransferencia;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import com.example.projeto_spring.service.jogador.validacoes.ValidadorJogador;
import com.example.projeto_spring.service.transferencia.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JogadorService {

    @Autowired
    private JogadorMapper jogadorMapper;

    @Autowired
    private List<ValidadorJogador> validadores;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;


    @Autowired
    private TransferenciaService transferenciaService;

    public Jogador cadastrar(DtoCadastroJogador dto) {
        validadores.forEach(v -> v.validar(dto));
        Jogador jogador = jogadorMapper.toEntity(dto);

        Time time = timeRepository.getReferenceById(dto.timeId());
        jogador.setTime(time);

        Nacionalidade nacionalidade = nacionalidadeRepository.getReferenceById(dto.nacionalidadeId());
        jogador.setNacionalidade(nacionalidade);

        jogadorRepository.save(jogador);

        DtoCadastroTransferencia dtoTransferencia = new DtoCadastroTransferencia(jogador.getId(), jogador.getTime().getId(), jogador.getContrato().getValorPago() == 0 ? 0 : jogador.getContrato().getValorPago() * -1, jogador.getContrato().getContratoInicio(), TipoTransferencia.COMPRA);
        transferenciaService.compra(dtoTransferencia);

        return jogador;
    }

    public List<Jogador> listar() {
        return jogadorRepository.findAll();
    }

    public Jogador listarJogador(UUID id) {
        return jogadorRepository.getReferenceById(id);
    }

    public List<Jogador> listarPorTime(UUID timeId) {
        return jogadorRepository.findByTimeId(timeId);
    }

    public Jogador atualizar(DtoAtualizarJogador dto, UUID id) {
        Optional<Jogador> jogadorOpt = jogadorRepository.findById(id);
        Jogador jogador = jogadorOpt.orElseThrow();

        if (dto.nome() != null) {
            jogador.setNome(dto.nome());
        }
        if (dto.nacionalidadeId() != null) {
            Nacionalidade nacionalidade = nacionalidadeRepository.getReferenceById(dto.nacionalidadeId());
            jogador.setNacionalidade(nacionalidade);
        }
        if (dto.numeroCamisa() != null) {
            jogador.setNumeroCamisa(dto.numeroCamisa());
        }
        if (dto.altura() != null) {
            jogador.setAltura(dto.altura());
        }
        if (dto.posicao() != null) {
            jogador.setPosicao(dto.posicao());
        }
        if (dto.contrato() != null && jogador.getContrato() != null) {
            jogador.getContrato().atualizarContrato(dto.contrato());
        }
        return jogador;
    }

    public void excluir(UUID id) {
        Jogador jogador = jogadorRepository.getReferenceById(id);
        DtoCadastroTransferencia dto = new DtoCadastroTransferencia(jogador.getId(), jogador.getTime().getId(), jogador.getContrato().getValorAtual(), LocalDate.now(), TipoTransferencia.VENDA);
        jogador.setTime(null);
        transferenciaService.venda(dto);
    }
}