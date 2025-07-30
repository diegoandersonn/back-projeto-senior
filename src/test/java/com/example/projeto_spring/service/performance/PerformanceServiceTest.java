package com.example.projeto_spring.service.performance;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.domain.Performance;
import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.dto.mapper.PerformanceMapper;
import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.dto.performance.UpdatePerformanceDto;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.repository.PerformanceRepository;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.service.performance.validations.PerformanceValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerformanceServiceTest {

    @InjectMocks
    PerformanceService service;

    @Mock
    public PerformanceMapper performanceMapper;
    @Mock
    public PerformanceRepository performanceRepository;
    @Mock
    public MatchRepository matchRepository;
    @Mock
    public PlayerRepository playerRepository;
    @Mock
    public PerformanceValidator validator1;
    @Mock
    public PerformanceValidator validator2;
    @Mock
    public PerformanceValidator validator3;

    public RegisterPerformanceDto dto;
    public Performance performance;
    public Performance performance2;
    public List<Performance> performances;
    public Match match;
    public Player player;
    public UUID performanceId = UUID.fromString("9c3dbcbd-12d1-468e-9d39-20e4547edcef");
    public UUID matchId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public UUID playerId = UUID.fromString("6b14a96d-c516-448f-adb2-170e16f8c760");
    public UUID teamId = UUID.fromString("7dcef104-ad47-48a5-9364-4034bae5445c");

    @BeforeEach
    void setUp() {
        service = new PerformanceService(performanceMapper, performanceRepository, matchRepository, playerRepository, List.of(validator1, validator2, validator3));
        dto = new RegisterPerformanceDto(matchId, playerId, 8.0);
        performance = new Performance();
        performance2 = new Performance();
        performances = new ArrayList<>();
        performances.add(performance);
        performances.add(performance2);
        match = new Match();
        player = new Player();
    }

    @Test
    void register_shouldValidateAndSavePerformance() {
        doNothing().when(validator1).validate(dto);
        doNothing().when(validator2).validate(dto);
        doNothing().when(validator3).validate(dto);
        when(performanceMapper.toEntity(dto)).thenReturn(performance);
        when(matchRepository.getReferenceById(matchId)).thenReturn(match);
        when(playerRepository.getReferenceById(playerId)).thenReturn(player);

        service.register(dto);

        verify(performanceRepository).save(performance);
        assertSame(match, performance.getMatch());
        assertSame(player, performance.getPlayer());
    }

    @Test
    void list_shouldReturnPerformanceList() {
        when(performanceRepository.findAll()).thenReturn(performances);

        assertEquals(performances, service.list());
        verify(performanceRepository).findAll();
    }

    @Test
    void list_shouldReturnPerformanceListByPlayerId() {
        when(performanceRepository.findByPlayerId(playerId)).thenReturn(performances);

        assertEquals(performances, service.listByPlayerId(playerId));
        verify(performanceRepository).findByPlayerId(playerId);
    }

    @Test
    void list_shouldReturnPerformanceListByTeamId() {
        when(performanceRepository.findByTeamId(teamId)).thenReturn(performances);

        assertEquals(performances, service.listByTeamId(teamId));
        verify(performanceRepository).findByTeamId(teamId);
    }

    @Test
    void update_shouldUpdate_whenRatingIsNotNull() {
        UpdatePerformanceDto updateDto = new UpdatePerformanceDto(9.5); // Usa o DTO real
        performance.setRating(8.0);

        when(performanceRepository.getReferenceById(performanceId)).thenReturn(performance);

        Performance updatedPerformance = service.update(performanceId, updateDto);

        assertEquals(9.5, updatedPerformance.getRating());
        verify(performanceRepository).getReferenceById(performanceId);
    }

    @Test
    void update_shouldNotUpdate_whenRatingIsNull() {
        UpdatePerformanceDto updateDto = new UpdatePerformanceDto(null); // Usa o DTO real
        performance.setRating(8.0);

        when(performanceRepository.getReferenceById(performanceId)).thenReturn(performance);

        Performance updatedPerformance = service.update(performanceId, updateDto);

        assertEquals(performance, updatedPerformance);
        verify(performanceRepository).getReferenceById(performanceId);
    }

    @Test
    void delete_shouldDelete() {
        service.delete(performanceId);

        verify(performanceRepository).deleteById(performanceId);
    }
}