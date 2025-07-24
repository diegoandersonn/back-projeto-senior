package com.example.projeto_spring.service.atuacao.validacoes;

import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaJogadorPertecenceAoTimeDaPartida implements ValidadorAtuacao {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    public void validar(DtoCadastroAtuacao dto) {
        var jogador = jogadorRepository.getReferenceById(dto.jogadorId());
        var timeId = jogador.getTime().getId();
        if (!partidaRepository.existsByTimeIdAndId(timeId, dto.partidaId())) {
            throw new IllegalArgumentException("Jogador não pode jogador uma partida que não é do seu time");
        }
    }
}
