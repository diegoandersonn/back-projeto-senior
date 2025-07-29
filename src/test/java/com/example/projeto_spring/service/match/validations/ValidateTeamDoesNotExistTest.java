package com.example.projeto_spring.service.match.validations;

import com.example.projeto_spring.dto.match.RegisterMatchDto;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ValidateTeamDoesNotExistTest {

    @InjectMocks
    public ValidateTeamDoesNotExist validator;

    @Mock
    public TeamRepository teamRepository;

    public RegisterMatchDto registerMatchDto;

    @BeforeEach
    public void setUp() {
        registerMatchDto = new RegisterMatchDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), LocalDate.now());
    }

    @Test
    void validate_shouldThrowException_whenTeamNotExists() {
        when(teamRepository.existsById(any())).thenReturn(false);
        assertThrows(EntityNotFoundException.class, () -> validator.validate(registerMatchDto));
    }

    @Test
    void validate_shouldNotThrowException_whenTeamExists() {
        when(teamRepository.existsById(any())).thenReturn(true);
        assertDoesNotThrow(() -> validator.validate(registerMatchDto));
    }
}