package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.infra.exception.PlayerNotInMatchTeamException;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePlayerBelongsToTeamTest {
    @InjectMocks
    ValidatePlayerBelongsToTeam validator;
    @Mock
    MatchRepository matchRepository;
    @Mock
    PlayerRepository playerRepository;
    @Mock
    TeamRepository teamRepository;

    public Player player;
    public Team team;
    public RegisterPerformanceDto dto;

    @BeforeEach
    public void setUp() {
        dto = new RegisterPerformanceDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), UUID.fromString("6b14a96d-c516-448f-adb2-170e16f8c760"), 8.0);
        player = new Player();
        team = new Team();
        player.setTeam(team);
    }

    @Test
    void validate_shouldThrowException_WhenPlayerNotInMatchTeam() {
        when(playerRepository.getReferenceById(dto.playerId())).thenReturn(player);
        when(matchRepository.existsByTeamIdAndId(player.getTeam().getId(), dto.matchId())).thenReturn(false);

        assertThrows(PlayerNotInMatchTeamException.class, () -> validator.validate(dto));
    }

    @Test
    void validate_shouldNotThrowException_WhenPlayerBelongsToMatchTeam () {
        when(playerRepository.getReferenceById(dto.playerId())).thenReturn(player);
        when(matchRepository.existsByTeamIdAndId(player.getTeam().getId(), dto.matchId())).thenReturn(true);

        assertDoesNotThrow(() -> validator.validate(dto));
    }
}