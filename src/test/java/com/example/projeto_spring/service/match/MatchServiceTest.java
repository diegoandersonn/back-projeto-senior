package com.example.projeto_spring.service.match;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.mapper.MatchMapper;
import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.repository.MatchRepository;
import com.example.projeto_spring.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

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

    @Captor
    ArgumentCaptor<Match> captor;
    public Match match1;
    public Match match2;
    public Team team2;
    public List<Match> matches;
    public RegisterMatchDto dto;
    public UUID teamId = UUID.fromString("89cd6d14-bff1-4d7c-8638-b884bc21b334");


    @Test
    void test() {

    }
}