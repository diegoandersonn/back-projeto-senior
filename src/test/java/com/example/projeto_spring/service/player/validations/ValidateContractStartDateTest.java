package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.enums.ContractType;
import com.example.projeto_spring.enums.Position;
import com.example.projeto_spring.infra.exception.InvalidTransferWindowException;
import com.example.projeto_spring.repository.NationalityRepository;
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
class ValidateContractStartDateTest {
    @InjectMocks
    public ValidateContractStartDate validator;

    @Mock
    public NationalityRepository nationalityRepository;

    public RegisterPlayerDto registerPlayerDto;
    public RegisterContractDto contractDto;
    public Nationality nationality;
    public UUID playerId = UUID.fromString("9c3dbcbd-12d1-468e-9d39-20e4547edcef");
    public UUID teamId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public UUID nationalityId = UUID.fromString("6b14a96d-c516-448f-adb2-170e16f8c760");


    @BeforeEach
    void setUp() {
        contractDto = new RegisterContractDto(
                100000.0,
                120000.0,
                120000.0,
                ContractType.PERMANENT,
                LocalDate.of(2025, 1, 10),
                LocalDate.of(2027, 12, 31)
        );

        registerPlayerDto = new RegisterPlayerDto(
                "João Silva",
                "João Pedro da Silva",
                10,
                1.82,
                Position.STRIKER,
                teamId,
                nationalityId,
                LocalDate.of(2000, 5, 20),
                contractDto
        );

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
        when(nationalityRepository.getReferenceById(nationalityId)).thenReturn(nationality);
        nationality.setContinent(continent);

        RegisterContractDto contract = new RegisterContractDto(
                12000.0,
                120000.0,
                paidValue,
                ContractType.PERMANENT,
                date,
                LocalDate.of(2027, 12, 31)
        );

        RegisterPlayerDto dto = new RegisterPlayerDto(
                "João Silva",
                "João Pedro da Silva",
                10,
                1.82,
                Position.STRIKER,
                teamId,
                nationalityId,
                LocalDate.of(2000, 5, 20),
                contract
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
        when(nationalityRepository.getReferenceById(nationalityId)).thenReturn(nationality);
        nationality.setContinent(continent);

        RegisterContractDto contract = new RegisterContractDto(
                12000.0,
                120000.0,
                paidValue,
                ContractType.PERMANENT,
                date,
                LocalDate.of(2027, 12, 31)
        );

        RegisterPlayerDto dto = new RegisterPlayerDto(
                "João Silva",
                "João Pedro da Silva",
                10,
                1.82,
                Position.STRIKER,
                teamId,
                nationalityId,
                LocalDate.of(2000, 5, 20),
                contract
        );

        assertThrows(InvalidTransferWindowException.class, () -> validator.validate(dto));
    }
}