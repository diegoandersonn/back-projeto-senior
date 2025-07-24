package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.dto.atuacao.DtoDetalhamentoAtuacao;
import com.example.projeto_spring.dto.atuacao.DtoListagemAtuacao;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.dto.time.DtoDetalhamentoTime;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.service.atuacao.AtuacaoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("atuacoes")
public class AtuacaoController {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    @Autowired
    private AtuacaoService atuacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroAtuacao dto, UriComponentsBuilder uriBuilder) {
        Atuacao atuacao = atuacaoService.cadastrar(dto);
        var uri = uriBuilder.path("/atuacoes/{id}").buildAndExpand(atuacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoAtuacao(atuacao));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Atuacao> atuacoes = atuacaoService.listar();
        return ResponseEntity.ok(atuacoes.stream().map(DtoListagemAtuacao::new).toList());
    }

    @GetMapping("/jogadores/{jogadorId}")
    public ResponseEntity listarPorJogadorId(@PathVariable UUID jogadorId) {
        List<Atuacao> atuacoes = atuacaoService.listarPorJogadorId(jogadorId);
        return ResponseEntity.ok(atuacoes.stream().map(DtoListagemAtuacao::new).toList());
    }

    @GetMapping("/times/{timeId}")
    public ResponseEntity listarPorTimeId(@PathVariable UUID timeId) {
        List<Atuacao> atuacoes = atuacaoService.listarPorTimeId(timeId);
        return ResponseEntity.ok(atuacoes.stream().map(DtoListagemAtuacao::new).toList());
    }

}
