package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.enums.TransferType;
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
class ValidateTransferTeamDoesNotExistTest {

    @InjectMocks
    public ValidateTransferTeamDoesNotExist validator;

    @Mock
    public TeamRepository teamRepository;

    public RegisterTransferDto dto;
    public Team team;
    public UUID teamId = UUID.randomUUID();
    public Nationality nationality;

    @BeforeEach
    void setUp() {
        dto = new RegisterTransferDto(UUID.randomUUID(), teamId, 100000.00, LocalDate.now(), TransferType.PURCHASE);
        team = new Team();
        nationality = new Nationality();
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