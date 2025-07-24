package com.example.projeto_spring.service.atuacao;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.dto.mapper.AtuacaoMapper;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import com.example.projeto_spring.service.atuacao.validacoes.ValidadorAtuacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AtuacaoService {

    @Autowired
    private AtuacaoMapper atuacaoMapper;

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private List<ValidadorAtuacao> validadores;

    public Atuacao cadastrar(DtoCadastroAtuacao dto) {
        validadores.forEach(v -> v.validar(dto));
        Atuacao atuacao = atuacaoMapper.toEntity(dto);

        Partida partida = partidaRepository.getReferenceById(dto.partidaId());
        atuacao.setPartida(partida);

        Jogador jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        atuacao.setJogador(jogador);

        atuacaoRepository.save(atuacao);
        return atuacao;
    }

    public List<Atuacao> listar() {
        return atuacaoRepository.findAll();
    }

    public List<Atuacao> listarPorJogadorId(UUID jogadorId) {
        return atuacaoRepository.findByJogadorId(jogadorId);
    }

    public List<Atuacao> listarPorTimeId(UUID timeId) {
        return atuacaoRepository.findByTimeId(timeId);
    }
}
