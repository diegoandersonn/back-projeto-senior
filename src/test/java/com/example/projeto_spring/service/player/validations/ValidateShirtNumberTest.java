package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.enums.ContractType;
import com.example.projeto_spring.enums.Position;
import com.example.projeto_spring.infra.exception.InvalidShirtNumberException;
import com.example.projeto_spring.repository.PlayerRepository;
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
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateShirtNumberTest {

    @InjectMocks
    public ValidateShirtNumber validator;

    @Mock
    public PlayerRepository playerRepository;

    public RegisterPlayerDto dto;
    public UUID teamId = UUID.randomUUID();
    public UUID playerId = UUID.randomUUID();
    public Player player;

    @BeforeEach
    void setUp() {
        player = new Player();

    }

    private static Stream<Arguments> provideInvalidShirtNumbers() {
        return Stream.of(
                Arguments.of(0),
                Arguments.of(-100000),
                Arguments.of(100),
                Arguments.of(100000)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidShirtNumbers")
    void shouldThrowException_WhenShirtNumberIsOutOfValidRange(int shirtNumber) {
        when(playerRepository.existsByTeamIdAndShirtNumber(teamId, shirtNumber)).thenReturn(false);
        RegisterContractDto contract = new RegisterContractDto(
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
                shirtNumber,
                1.82,
                Position.STRIKER,
                teamId,
                UUID.randomUUID(),
                LocalDate.of(2000, 5, 20),
                contract
        );

        assertThrows(InvalidShirtNumberException.class, () -> validator.validate(dto));
    }

    private static Stream<Arguments> provideValidShirtNumbers() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(99),
                Arguments.of(50),
                Arguments.of(10)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidShirtNumbers")
    void shouldNotThrowException_WhenShirtNumberIsValidAndAvailable(int shirtNumber) {
        when(playerRepository.existsByTeamIdAndShirtNumber(teamId, shirtNumber)).thenReturn(false);
        RegisterContractDto contract = new RegisterContractDto(
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
                shirtNumber,
                1.82,
                Position.STRIKER,
                teamId,
                UUID.randomUUID(),
                LocalDate.of(2000, 5, 20),
                contract
        );

        assertDoesNotThrow(() -> validator.validate(dto));
    }

    @Test
    void shouldResetShirtNumberToZero_WhenNumberIsAlreadyInUseByAnotherPlayer() {
        when(playerRepository.existsByTeamIdAndShirtNumber(teamId, 9)).thenReturn(true);
        when(playerRepository.findByTeamIdAndShirtNumber(teamId, 9)).thenReturn(player);

        RegisterContractDto contract = new RegisterContractDto(
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
                UUID.randomUUID(),
                LocalDate.of(2000, 5, 20),
                contract
        );
        validator.validate(dto);

        assertEquals(0, player.getShirtNumber());
    }
}