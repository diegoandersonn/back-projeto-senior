package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.dto.match.DetailMatchDto;
import com.example.projeto_spring.dto.match.ListMatchDto;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.service.match.MatchService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("matches")
public class MatchController {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private MatchService matchService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterMatchDto dto, UriComponentsBuilder uriBuilder) {
        Match match = matchService.register(dto);
        var uri = uriBuilder.path("/matches/{id}").buildAndExpand(match.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailMatchDto(match));
    }

    @GetMapping
    public ResponseEntity list() {
        List<Match> matches = matchService.list();
        return ResponseEntity.ok(matches.stream().map(ListMatchDto::new).toList());
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity listByTeam(@PathVariable UUID teamId) {
        List<Match> matches = matchService.listByTeamId(teamId);
        return ResponseEntity.ok(matches.stream().map(ListMatchDto::new).toList());
    }
}
