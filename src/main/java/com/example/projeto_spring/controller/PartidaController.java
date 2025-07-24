package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.atuacao.DtoDetalhamentoAtuacao;
import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import com.example.projeto_spring.dto.partida.DtoDetalhamentoPartida;
import com.example.projeto_spring.dto.partida.DtoListagemPartida;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import com.example.projeto_spring.service.partida.PartidaService;
import jakarta.servlet.http.Part;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping
    public ResponseEntity listar() {
        List<Partida> partidas = partidaService.listar();
        return ResponseEntity.ok(partidas.stream().map(DtoListagemPartida::new).toList());
    }

    @GetMapping("/times/{timeId}")
    public ResponseEntity listarPorTime(@PathVariable UUID timeId) {
        List<Partida> partidas = partidaService.listarPorTime(timeId);
        return ResponseEntity.ok(partidas.stream().map(DtoListagemPartida::new).toList());
    }
}
