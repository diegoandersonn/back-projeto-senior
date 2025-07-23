package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.dto.atuacao.DtoDetalhamentoAtuacao;
import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.dto.partida.DtoDetalhamentoPartida;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import com.example.projeto_spring.service.partida.PartidaService;
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
@RequestMapping("partidas")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepository;

    @Autowired
    private PartidaService partidaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroPartida dto, UriComponentsBuilder uriBuilder) {
        Partida partida = partidaService.cadastrar(dto);
        var uri = uriBuilder.path("/partidas/{id}").buildAndExpand(partida.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoPartida(partida));
    }

}
