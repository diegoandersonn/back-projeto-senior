package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.time.DtoAtualizarTime;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import com.example.projeto_spring.dto.time.DtoDetalhamentoTime;
import com.example.projeto_spring.dto.time.DtoListarTime;
import com.example.projeto_spring.repository.TimeRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DtoCadastroTime dto, UriComponentsBuilder uriBuilder) {
        Time time = new Time(dto);
        timeRepository.save(time);
        var uri = uriBuilder.path("/times/${id}").buildAndExpand(time.getId()).toUri();
        return ResponseEntity.created(uri).body(new DtoDetalhamentoTime(time));
    }

    @GetMapping
    public ResponseEntity listar() {
        List<Time> times = timeRepository.findAll();
        return ResponseEntity.ok(times);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> listarTimesPorUsuarios(@PathVariable UUID usuarioId) {
        Optional<Time> time = timeRepository.findByUsuarioId(usuarioId);
        return time
                .map(j -> ResponseEntity.ok(new DtoListarTime(j)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DtoAtualizarTime dto) {
        Time time = timeRepository.getReferenceById(dto.id());
        time.atualizar(dto);
        return ResponseEntity.ok(new DtoDetalhamentoTime(time));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable UUID id) {
        timeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
