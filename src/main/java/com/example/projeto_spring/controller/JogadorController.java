package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.dto.jogador.DtoAtualizarJogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.dto.jogador.DtoDetalhamentoJogador;
import com.example.projeto_spring.dto.jogador.DtoListagemJogador;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import com.example.projeto_spring.service.jogador.JogadorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private JogadorService jogadorService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroJogador dto, UriComponentsBuilder uriBuilder) {
        Jogador jogador = jogadorService.cadastrar(dto);
        var uri = uriBuilder.path("/jogadores/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoJogador(jogador));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Jogador> jogadores = jogadorService.listar();
        return ResponseEntity.ok(jogadores.stream().map(DtoListagemJogador::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity listarJogador(@PathVariable UUID id) {
        Jogador jogador = jogadorService.listarJogador(id);
        return ResponseEntity.ok(new DtoListagemJogador(jogador));
    }

    @GetMapping("/time/{timeId}")
    public ResponseEntity listarPorTime(@PathVariable UUID timeId) {
        List<Jogador> jogadores = jogadorService.listarPorTime(timeId);
        return ResponseEntity.ok(jogadores.stream().map(DtoListagemJogador::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarJogador dto, @PathVariable UUID id) {
        Jogador jogador = jogadorService.atualizar(dto, id);
        return ResponseEntity.ok(new DtoDetalhamentoJogador(jogador));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable UUID id) {
        jogadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
