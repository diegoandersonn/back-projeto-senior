package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.time.DtoCadastroTime;
import com.example.projeto_spring.domain.time.DtoDetalhamentoTime;
import com.example.projeto_spring.domain.time.Time;
import com.example.projeto_spring.domain.time.TimeRepository;
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
}
