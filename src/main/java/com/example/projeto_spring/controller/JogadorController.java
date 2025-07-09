package com.example.projeto_spring.controller;


import com.example.projeto_spring.domain.jogador.DadosCadastroJogador;
import com.example.projeto_spring.domain.jogador.DadosDetalhamentoJogador;
import com.example.projeto_spring.domain.jogador.Jogador;
import com.example.projeto_spring.domain.jogador.JogadorRepository;
import com.example.projeto_spring.domain.time.Time;
import com.example.projeto_spring.domain.time.TimeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroJogador dto, UriComponentsBuilder uriBuilder) {
        Jogador jogador = new Jogador(dto);
        Time time = timeRepository.findTimeById(dto.timeId());
        jogador.setTime(time);
        jogadorRepository.save(jogador);
        var uri = uriBuilder.path("/jogadores/{id").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoJogador(jogador));
    }
}
