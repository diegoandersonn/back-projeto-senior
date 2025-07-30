package com.example.projeto_spring.service.match;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.mapper.MatchMapper;
import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.match.validations.MatchValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchServiceTest {

    @InjectMocks
    public MatchService service;
    @Mock
    public MatchMapper matchMapper;
    @Mock
    public MatchRepository matchRepository;
    @Mock
    public TeamRepository teamRepository;
    @Mock
    private MatchValidator validator1;
    @Mock
    private MatchValidator validator2;

    public Match match1;
    public List<Match> matches;
    public Team team1;
    public RegisterMatchDto dto;
    public UUID teamId = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");


    @BeforeEach
    void setUp() {
        service = new MatchService(matchMapper, matchRepository, teamRepository, List.of(validator1, validator2));
        dto = new RegisterMatchDto(teamId, LocalDate.now());
        team1 = new Team();
        match1 = new Match();
        matches = new ArrayList<>();
    }

    @Test
    void register_shouldValidateAndSaveMatch() {
        doNothing().when(validator1).validate(dto);
        doNothing().when(validator2).validate(dto);
        when(matchMapper.toEntity(dto)).thenReturn(match1);
        when(teamRepository.getReferenceById(dto.teamId())).thenReturn(team1);

        service.register(dto);

        verify(matchRepository).save(match1);
        assertSame(team1, match1.getTeam());
    }

    @Test
    void list_shouldReturnMatchesList() {
        when(matchRepository.findAll()).thenReturn(matches);

        assertEquals(matches, service.list());
        verify(matchRepository).findAll();
    }

    @Test
    void list_shouldReturnMatchesListByTeamId() {
        when(matchRepository.findMatchByTeamId(any())).thenReturn(matches);

        assertEquals(matches, service.listByTeamId(any()));
        verify(matchRepository).findMatchByTeamId(any());
    }
}