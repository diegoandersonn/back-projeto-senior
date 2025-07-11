package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.jogador.DtoDetalhamentoJogador;
import com.example.projeto_spring.domain.nacionalidade.DtoCadastroNacionalidade;
import com.example.projeto_spring.domain.nacionalidade.DtoDetalhamentoNacionalidade;
import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import com.example.projeto_spring.domain.nacionalidade.NacionalidadeRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("nacionalidades")
public class NacionalidadeController {
    @Autowired
    NacionalidadeRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DtoCadastroNacionalidade dto, UriComponentsBuilder uriBuilder) {
        Nacionalidade nacionalidade = new Nacionalidade(dto);
        System.out.println(nacionalidade.getId());
        repository.save(nacionalidade);
        var uri = uriBuilder.path("nacionalidades/{id}").buildAndExpand(nacionalidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoNacionalidade(nacionalidade));
    }
}
