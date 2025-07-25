package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.dto.player.UpdatePlayerDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.dto.player.DetailPlayerDto;
import com.example.projeto_spring.dto.player.ListPlayerDto;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TransferRepository;
import com.example.projeto_spring.service.player.PlayerService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("players")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private PlayerService playerService;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterPlayerDto dto, UriComponentsBuilder uriBuilder) {
        Player player = playerService.register(dto);
        var uri = uriBuilder.path("/players/{id}").buildAndExpand(player.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPlayerDto(player));
    }

    @GetMapping
    public ResponseEntity list() {
        List<Player> players = playerService.list();
        return ResponseEntity.ok(players.stream().map(ListPlayerDto::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity listPlayerbyId(@PathVariable UUID id) {
        Player player = playerService.listPlayerById(id);
        return ResponseEntity.ok(new ListPlayerDto(player));
    }

    @GetMapping("/team/{teamId}")
    public ResponseEntity listPlayerByTeamId(@PathVariable UUID teamId) {
        List<Player> players = playerService.listPlayerByTeamId(teamId);
        return ResponseEntity.ok(players.stream().map(ListPlayerDto::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdatePlayerDto dto, @PathVariable UUID id) {
        Player player = playerService.update(dto, id);
        return ResponseEntity.ok(new DetailPlayerDto(player));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        playerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
