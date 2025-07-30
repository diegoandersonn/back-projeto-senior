package com.example.projeto_spring.service.team;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.domain.User;
import com.example.projeto_spring.dto.mapper.TeamMapper;
import com.example.projeto_spring.dto.team.RegisterTeamDto;
import com.example.projeto_spring.dto.team.UpdateTeamDto;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.UserRepository;
import com.example.projeto_spring.service.team.validations.TeamValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceTest {
    @InjectMocks
    public TeamService service;

    @Mock
    public TeamValidator teamValidator1;

    @Mock
    public TeamMapper teamMapper;

    @Mock
    public TeamRepository teamRepository;

    @Mock
    public NationalityRepository nationalityRepository;

    @Mock
    public UserRepository userRepository;

    public RegisterTeamDto dto;
    public UUID nationalityId = UUID.randomUUID();
    public UUID userId = UUID.randomUUID();
    public UUID teamId = UUID.randomUUID();
    public Team team1;
    public Team team2;
    public List<Team> teams;
    public User user;
    public Nationality nationality;

    @BeforeEach
    void setUp() {
        service = new TeamService(List.of(teamValidator1), teamMapper, teamRepository, nationalityRepository, userRepository);
        dto = new RegisterTeamDto("São Paulo", "Morumbis", 2000000.00, nationalityId, userId);
        team1 = new Team();
        team2 = new Team();
        teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        user = new User();
        nationality = new Nationality();
    }

    @Test
    void shouldRegisterTeam_WhenDtoIsValidAndDependenciesExist() {
        doNothing().when(teamValidator1).validate(dto);
        when(teamMapper.toEntity(dto)).thenReturn(team1);
        when(userRepository.getReferenceById(userId)).thenReturn(user);
        when(nationalityRepository.getReferenceById(nationalityId)).thenReturn(nationality);

        service.register(dto);

        verify(teamRepository).save(team1);
        assertEquals(user, team1.getUser());
        assertEquals(nationality, team1.getNationality());
    }

    @Test
    void shouldReturnAllTeams_WhenListIsCalled() {
        when(teamRepository.findAll()).thenReturn(teams);

        assertEquals(teams, service.list());
    }

    @Test
    void shouldReturnTeamById_WhenIdExists() {
        when(teamRepository.getReferenceById(teamId)).thenReturn(team1);

        assertEquals(team1, service.listTeamById(teamId));
    }

    @Test
    void shouldReturnTeamsByUserId_WhenUserIdExists() {
        when(teamRepository.findAllByUserId(userId)).thenReturn(teams);

        assertEquals(teams, service.listTeamByUserId(userId));
    }

    private static Stream<Arguments> provideValidUpdateTeamData() {
        return Stream.of(
                Arguments.of("São Paulo", "Morumbis", 15000000.00),
                Arguments.of("São Paulo", null, null),
                Arguments.of(null, "Morumbis", null),
                Arguments.of(null, null, 15000000.00)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidUpdateTeamData")
    void shouldUpdateTeamWithoutException_WhenValidUpdateDtoProvided(String name, String stadium, Double transferBalance) {
        when(teamRepository.getReferenceById(teamId)).thenReturn(team1);

        UpdateTeamDto updateDto = new UpdateTeamDto(name, stadium, transferBalance);
        service.update(updateDto, teamId);

        assertDoesNotThrow(() -> service.update(updateDto, teamId));
    }

    @Test
    void shouldDeleteTeam_WhenTeamIdIsValid() {
        service.delete(teamId);

        verify(teamRepository).deleteById(teamId);
    }
}