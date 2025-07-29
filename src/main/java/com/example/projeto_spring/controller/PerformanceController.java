package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Performance;
import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.dto.performance.DetailPerformanceDto;
import com.example.projeto_spring.dto.performance.ListPerformanceDto;
import com.example.projeto_spring.dto.performance.UpdatePerformanceDto;
import com.example.projeto_spring.repository.PerformanceRepository;
import com.example.projeto_spring.service.performance.PerformanceService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("performances")
public class PerformanceController {

    @Autowired
    private PerformanceRepository performanceRepository;

    @Autowired
    private PerformanceService performanceService;

    @PostMapping
    @Transactional
    public ResponseEntity registerPerformance(@RequestBody @Valid RegisterPerformanceDto dto, UriComponentsBuilder uriBuilder) {
        Performance performance = performanceService.register(dto);
        var uri = uriBuilder.path("/performances/{id}").buildAndExpand(performance.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPerformanceDto(performance));
    }

    @GetMapping
    public ResponseEntity listPerformance() {
        List<Performance> performances = performanceService.list();
        return ResponseEntity.ok(performances.stream().map(ListPerformanceDto::new).toList());
    }

    @GetMapping("/players/{playerId}")
    public ResponseEntity listByPlayerId(@PathVariable UUID playerId) {
        List<Performance> performances = performanceService.listByPlayerId(playerId);
        return ResponseEntity.ok(performances.stream().map(ListPerformanceDto::new).toList());
    }

    @GetMapping("/teams/{teamId}")
    public ResponseEntity listByTeamId(@PathVariable UUID teamId) {
        List<Performance> performances = performanceService.listByTeamId(teamId);
        return ResponseEntity.ok(performances.stream().map(ListPerformanceDto::new).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity updatePerformance(@PathVariable UUID id, @RequestBody @Valid UpdatePerformanceDto dto) {
        Performance performance = performanceService.update(id, dto);
        return ResponseEntity.ok(new DetailPerformanceDto(performance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePerformance(@PathVariable UUID id) {
        performanceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
