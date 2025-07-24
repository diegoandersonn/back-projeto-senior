package com.example.projeto_spring.service.atuacao.validacoes;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.JogadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaNumeroDeAtuacoesPorPartida implements ValidadorAtuacao {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    public void validar(DtoCadastroAtuacao dto) {
        if (atuacaoRepository.existsByJogadorIdAndPartidaId(dto.jogadorId(), dto.partidaId())) {
            throw new IllegalArgumentException("Jogador não pode ter duas atuações por partida");
        }
    }
}
