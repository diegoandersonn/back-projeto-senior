package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.infra.exception.InvalidTransferDateException;
import com.example.projeto_spring.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
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

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateInvalidTransferDateTest {

    @InjectMocks
    public ValidateInvalidTransferDate validator;

    @Mock
    public TeamRepository teamRepository;

    public RegisterTransferDto dto;
    public Team team;
    public UUID teamId = UUID.randomUUID();
    public Nationality nationality;

    @BeforeEach
    void setUp() {
        dto = new RegisterTransferDto(UUID.randomUUID(), UUID.randomUUID(), 100000.00, LocalDate.now(), TransferType.PURCHASE);
        team = new Team();
        nationality = new Nationality();
    }

    private static Stream<Arguments> provideValidTransferWindowData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2025, 1, 1), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 4, 16), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 6, 22), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 9, 2), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 10, 1), "Americas", 0.00),
                Arguments.of(LocalDate.of(2025, 1, 1), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 2, 2), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 7, 1), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 8, 31), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 10, 1), "Europe", 0.00),
                Arguments.of(LocalDate.of(2025, 1, 1), "Asia", 1000000.00),
                Arguments.of(LocalDate.of(2025, 1, 31), "Africa", 1000000.00),
                Arguments.of(LocalDate.of(2025, 7, 1), "Africa", 1000000.00),
                Arguments.of(LocalDate.of(2025, 8, 31), "Africa", 1000000.00),
                Arguments.of(LocalDate.of(2025, 10, 1), "Oceania", 0.00)
        );
    }

    @ParameterizedTest
    @MethodSource("provideValidTransferWindowData")
    void shouldNotThrowException_WhenContractStartDateIsValid(LocalDate date, String continent, Double paidValue) {
        when(teamRepository.getReferenceById(teamId)).thenReturn(team);
        nationality.setContinent(continent);
        team.setNationality(nationality);

        RegisterTransferDto dto = new RegisterTransferDto(
                UUID.randomUUID(),
                teamId,
                paidValue,
                date,
                TransferType.PURCHASE
        );

        assertDoesNotThrow(() -> validator.validate(dto));
    }

    private static Stream<Arguments> provideInvalidTransferWindowData() {
        return Stream.of(
                Arguments.of(LocalDate.of(2025, 12, 31), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 4, 17), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 6, 21), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 9, 3), "Americas", 1000000.00),
                Arguments.of(LocalDate.of(2025, 12, 31), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 2, 3), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 6, 30), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 9, 1), "Europe", 1000000.00),
                Arguments.of(LocalDate.of(2025, 12, 31), "Asia", 1000000.00),
                Arguments.of(LocalDate.of(2025, 2, 1), "Africa", 1000000.00),
                Arguments.of(LocalDate.of(2025, 6, 30), "Africa", 1000000.00),
                Arguments.of(LocalDate.of(2025, 9, 1), "Oceania",1000000.00)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTransferWindowData")
    void shouldThrowException_WhenContractStartDateIsInvalid(LocalDate date, String continent, Double paidValue) {
        when(teamRepository.getReferenceById(teamId)).thenReturn(team);
        nationality.setContinent(continent);
        team.setNationality(nationality);

        RegisterTransferDto dto = new RegisterTransferDto(
                UUID.randomUUID(),
                teamId,
                paidValue,
                date,
                TransferType.PURCHASE
        );

        assertThrows(InvalidTransferDateException.class, () -> validator.validate(dto));
    }
}