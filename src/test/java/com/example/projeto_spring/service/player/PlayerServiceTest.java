package com.example.projeto_spring.service.player;

import com.example.projeto_spring.domain.Contract;
import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.dto.contract.UpdateContractDto;
import com.example.projeto_spring.dto.mapper.PlayerMapper;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.dto.player.UpdatePlayerDto;
import com.example.projeto_spring.enums.ContractType;
import com.example.projeto_spring.enums.Position;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.player.validations.PlayerValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @InjectMocks
    public PlayerService service;

    @Mock
    public PlayerMapper playerMapper;
    @Mock
    public PlayerRepository playerRepository;
    @Mock
    public TeamRepository teamRepository;
    @Mock
    public NationalityRepository nationalityRepository;
    @Mock
    public PlayerValidator validator1;
    @Mock
    public PlayerValidator validator2;
    @Mock
    public PlayerValidator validator3;

    public RegisterPlayerDto dto;
    public UpdatePlayerDto updateDto;
    public UUID teamId = UUID.randomUUID();
    public UUID playerId = UUID.randomUUID();
    public UUID nationalityId = UUID.randomUUID();
    public Player player;
    public Player player2;
    public List<Player> players;
    public Team team;
    public Contract contract;
    public Nationality nationality;

    @BeforeEach
    void setUp() {
        service = new PlayerService(playerMapper, List.of(validator1, validator2, validator3), playerRepository, nationalityRepository, teamRepository);
        RegisterContractDto registerContractDto = new RegisterContractDto(
                12000.0,
                120000.0,
                120000.0,
                ContractType.PERMANENT,
                LocalDate.of(2023, 1, 1),
                LocalDate.of(2027, 12, 31)
        );

        dto = new RegisterPlayerDto(
                "João Silva",
                "João Pedro da Silva",
                9,
                1.82,
                Position.STRIKER,
                teamId,
                nationalityId,
                LocalDate.of(2000, 5, 20),
                registerContractDto
        );

        player = new Player();
        player2 = new Player();
        players = new ArrayList<>();
        players.add(player);
        players.add(player2);
        team = new Team();
        nationality = new Nationality();
        contract = new Contract();
    }

    @Test
    void shouldRegisterPlayer_WhenDtoIsValidAndAllDependenciesExist() {
        doNothing().when(validator1).validate(dto);
        doNothing().when(validator2).validate(dto);
        doNothing().when(validator3).validate(dto);

        when(playerMapper.toEntity(dto)).thenReturn(player);
        when(teamRepository.getReferenceById(teamId)).thenReturn(team);
        when(nationalityRepository.getReferenceById(nationalityId)).thenReturn(nationality);

        service.register(dto);

        verify(playerRepository).save(player);
        assertEquals(player.getTeam(), team);
        assertEquals(player.getNationality(), nationality);
    }

    @Test
    void shouldReturnAllPlayers_WhenPlayersHaveTeam() {
        when(playerRepository.findAllByTeamIdNotNull()).thenReturn(players);

        assertEquals(players, service.list(""));
    }

    @Test
    void shouldReturnPlayerById_WhenPlayerExists() {
        when(playerRepository.getReferenceById(playerId)).thenReturn(player);

        assertEquals(player, service.listPlayerById(playerId));
    }

    @Test
    void shouldReturnPlayersByTeamId_WhenTeamExists() {
        when(playerRepository.findByTeamId(teamId)).thenReturn(players);

        assertEquals(players, service.listPlayerByTeamId(teamId, ""));
    }

    private static Stream<Arguments> provideValidUpdatePlayerData() {
        return Stream.of(
                Arguments.of("Diego", UUID.randomUUID(), 69, 171.00, Position.GOALKEEPER, new UpdateContractDto(100000.00, 1000000.00, ContractType.PERMANENT, LocalDate.of(2029, 12, 31))),
                Arguments.of("Diego", null, null, null, null, null),
                Arguments.of(null, UUID.randomUUID(), null, null, null, null),
                Arguments.of(null, null, 69, null, null, null),
                Arguments.of(null, null, null, 171.00, null, null),
                Arguments.of(null, null, null, null, Position.GOALKEEPER, null),
                Arguments.of(null, null, null, null, null, new UpdateContractDto(100000.00, 1000000.00, ContractType.PERMANENT, LocalDate.of(2029, 12, 31)))
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidUpdatePlayerData")
    void shouldUpdatePlayerWithoutException_WhenValidDataIsProvided(String name, UUID nationalityId, Integer shirtNumber, Double height, Position position, UpdateContractDto updateContractDto) {
        when(playerRepository.getReferenceById(playerId)).thenReturn(player);
        if (nationalityId != null) {
            when(nationalityRepository.getReferenceById(nationalityId)).thenReturn(nationality);
        }
        if (updateContractDto != null) {
            player.setContract(contract);
        }

        UpdatePlayerDto updatePlayerDto = new UpdatePlayerDto(name, shirtNumber, height, position, nationalityId, updateContractDto);
        service.update(updatePlayerDto, playerId);

        assertDoesNotThrow(() -> service.update(updatePlayerDto, playerId));
    }

    @Test
    void shouldDeletePlayerById_WhenPlayerExists() {
        service.delete(playerId);

        verify(playerRepository).deleteById(playerId);
    }
}