package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.enums.ContractType;
import com.example.projeto_spring.enums.Position;
import com.example.projeto_spring.repository.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidatePlayerTeamDoesNotExistTest {
    @InjectMocks
    public ValidatePlayerTeamDoesNotExist validator;

    @Mock
    public TeamRepository teamRepository;

    public RegisterPlayerDto dto;
    public UUID teamId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
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
                10,
                1.82,
                Position.STRIKER,
                teamId,
                UUID.randomUUID(),
                LocalDate.of(2000, 5, 20),
                contract
        );
    }

    @Test
    void shouldThrowException_WhenTeamDoesNotExist() {
        when(teamRepository.existsById(teamId)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> validator.validate(dto));
    }

    @Test
    void shouldNotThrowException_WhenTeamExists() {
        when(teamRepository.existsById(teamId)).thenReturn(true);

        assertDoesNotThrow(() -> validator.validate(dto));
    }
}