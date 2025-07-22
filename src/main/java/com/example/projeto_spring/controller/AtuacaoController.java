package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import com.example.projeto_spring.dto.atuacao.DtoDetalhamentoAtuacao;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.dto.time.DtoDetalhamentoTime;
import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.service.atuacao.AtuacaoService;
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
public class AtuacaoController {

    @Autowired
    private AtuacaoRepository atuacaoRepository;

    @Autowired
    private AtuacaoService atuacaoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroAtuacao dto, UriComponentsBuilder uriBuilder) {
        Atuacao atuacao = atuacaoService.cadastrar(dto);
        var uri = uriBuilder.path("/times/${id}").buildAndExpand(atuacao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoAtuacao(atuacao));
    }

}
