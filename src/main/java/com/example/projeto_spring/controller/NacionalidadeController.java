package com.example.projeto_spring.controller;

import com.example.projeto_spring.dto.nacionalidade.DtoCadastroNacionalidade;
import com.example.projeto_spring.dto.nacionalidade.DtoDetalhamentoNacionalidade;
import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import com.example.projeto_spring.service.NacionalidadeService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("nacionalidades")
public class NacionalidadeController {
    @Autowired
    NacionalidadeRepository repository;
    @Autowired
    private NacionalidadeService service;

    @PostMapping
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid DtoCadastroNacionalidade dto, UriComponentsBuilder uriBuilder) {
        Nacionalidade nacionalidade = new Nacionalidade(dto);
        System.out.println(nacionalidade.getId());
        repository.save(nacionalidade);
        var uri = uriBuilder.path("nacionalidades/{id}").buildAndExpand(nacionalidade.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoNacionalidade(nacionalidade));
    }

    @PostMapping("/importar")
    public ResponseEntity importar() {
        service.importarNacionalidades();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Nacionalidade> nacionalidades = repository.findAll();
        return ResponseEntity.ok(nacionalidades);
    }
}
