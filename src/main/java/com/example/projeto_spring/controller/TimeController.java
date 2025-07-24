package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.jogador.DtoListagemJogador;
import com.example.projeto_spring.dto.time.DtoAtualizarTime;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.dto.time.DtoDetalhamentoTime;
import com.example.projeto_spring.dto.time.DtoListagemTime;
import com.example.projeto_spring.repository.TimeRepository;
import com.example.projeto_spring.service.time.TimeService;
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
@RequestMapping("times")
public class TimeController {

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private TimeService timeService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroTime dto, UriComponentsBuilder uriBuilder) {
        Time time = timeService.cadastrar(dto);
        var uri = uriBuilder.path("/times/{id}").buildAndExpand(time.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoTime(time));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Time> times = timeService.listar();
        return ResponseEntity.ok(times.stream().map(DtoListagemTime::new).toList());
    }

    @GetMapping("/{timeId}")
    public ResponseEntity listarTimesPorId(@PathVariable UUID timeId) {
        Time time = timeService.listarTimePorId(timeId);
        return ResponseEntity.ok(new DtoListagemTime(time));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity listarTimesPorUsuarios(@PathVariable UUID usuarioId) {
        List<Time> times = timeService.listarTimesPorUsuarios(usuarioId);
        return ResponseEntity.ok(times.stream().map(DtoListagemTime::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarTime dto, @PathVariable UUID id) {
        Time time = timeService.atualizar(dto, id);
        return ResponseEntity.ok(new DtoDetalhamentoTime(time));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable UUID id) {
        timeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
