package com.example.projeto_spring.service.team.validations;

import com.example.projeto_spring.dto.team.RegisterTeamDto;
import com.example.projeto_spring.infra.exception.DuplicatedTeamNameException;
import com.example.projeto_spring.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateDuplicatedTeamNameTest {

    @InjectMocks
    public ValidateDuplicatedTeamName validator;

    @Mock
    public TeamRepository teamRepository;

    public RegisterTeamDto dto;
    public UUID nationalityId = UUID.randomUUID();
    public UUID userId = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        dto = new RegisterTeamDto("São Paulo", "Morumbis", 2000000.00, nationalityId, userId);
    }

    @Test
    void shouldThrowException_WhenTeamNameAlreadyExists() {
        when(teamRepository.existsByName(dto.name())).thenReturn(true);

        assertThrows(DuplicatedTeamNameException.class, () -> validator.validate(dto));
    }
    @Test
    void shouldNotThrowException_WhenTeamNameIsUnique() {
        when(teamRepository.existsByName(dto.name())).thenReturn(false);

        assertDoesNotThrow(() -> validator.validate(dto));
    }
}