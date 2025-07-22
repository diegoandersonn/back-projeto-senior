package com.example.projeto_spring.service.atuacao;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtuacaoService {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public Atuacao cadastrar(DtoCadastroAtuacao dto) {
        Atuacao atuacao = toEntity(dto);
        atuacaoRepository.save(atuacao);
        return atuacao;
    }

    private Atuacao toEntity(DtoCadastroAtuacao dto) {
        Partida partida = partidaRepository.getReferenceById(dto.partidaId());
        Jogador jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        return new Atuacao(null, partida, jogador, dto.nota());
    }
}
