package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.jogador.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroJogador dto, UriComponentsBuilder uriBuilder) {
        Jogador jogador = new Jogador(dto);
        jogadorRepository.save(jogador);
        var uri = uriBuilder.path("/jogadores/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoJogador(jogador));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarJogador(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador
                .map(j -> ResponseEntity.ok(new DtoListarJogador(j)))
                .orElse(ResponseEntity.notFound().build());
    }
}
