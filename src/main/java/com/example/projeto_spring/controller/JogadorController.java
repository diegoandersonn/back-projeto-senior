package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.jogador.DtoAtualizarJogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.dto.jogador.DtoDetalhamentoJogador;
import com.example.projeto_spring.dto.jogador.DtoListarJogador;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.repository.JogadorRepository;
import com.example.projeto_spring.repository.TransferenciaRepository;
import com.example.projeto_spring.validators.jogador.ValidaJogador;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("jogadores")
public class JogadorController {
    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ValidaJogador validaJogador;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroJogador dto, UriComponentsBuilder uriBuilder) {
        Jogador jogador = validaJogador.validar(dto);
        var uri = uriBuilder.path("/jogadores/{id}").buildAndExpand(jogador.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoJogador(jogador));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Jogador> jogadores = jogadorRepository.findAll();
        return ResponseEntity.ok(jogadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarJogador(@PathVariable Long id) {
        Optional<Jogador> jogador = jogadorRepository.findById(id);
        return jogador
                .map(j -> ResponseEntity.ok(new DtoListarJogador(j)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarJogador dto) {
        Jogador jogador = jogadorRepository.getReferenceById(dto.id());
        jogador.atualizar(dto);
        return ResponseEntity.ok(new DtoDetalhamentoJogador(jogador));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity transferir(@PathVariable Long id) {
        Jogador jogador = jogadorRepository.getReferenceById(id);
        DtoCadastroTransferencia dtoTransferencia = new DtoCadastroTransferencia(jogador.getId(), jogador.getTimeId(), jogador.getValorPago(), LocalDate.now());
        Transferencia transferencia = new Transferencia(dtoTransferencia);
        transferenciaRepository.save(transferencia);
        jogador.excluir();
        return ResponseEntity.noContent().build();
    }
}
