package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.team.UpdateTeamDto;
import com.example.projeto_spring.dto.team.RegisterTeamDto;
import com.example.projeto_spring.dto.team.DetailTeamDto;
import com.example.projeto_spring.dto.team.ListTeamDto;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.team.TeamService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("teams")
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @PostMapping
    @Transactional
    public ResponseEntity registerTeam(@RequestBody @Valid RegisterTeamDto dto, UriComponentsBuilder uriBuilder) {
        Team team = teamService.register(dto);
        var uri = uriBuilder.path("/teams/{id}").buildAndExpand(team.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailTeamDto(team));
    }

    @GetMapping
    public ResponseEntity listTeams() {
        List<Team> teams = teamService.list();
        return ResponseEntity.ok(teams.stream().map(ListTeamDto::new).toList());
    }

    @GetMapping("/{teamId}")
    public ResponseEntity listTeamById(@PathVariable UUID teamId) {
        Team team = teamService.listTeamById(teamId);
        return ResponseEntity.ok(new ListTeamDto(team));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity listTeamByUserId(@PathVariable UUID userId) {
        List<Team> teams = teamService.listTeamByUserId(userId);
        return ResponseEntity.ok(teams.stream().map(ListTeamDto::new).toList());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateTeamDto dto, @PathVariable UUID id) {
        Team team = teamService.update(dto, id);
        return ResponseEntity.ok(new DetailTeamDto(team));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable UUID id) {
        teamService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
