package com.example.projeto_spring.service.performance.validations;

import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import com.example.projeto_spring.infra.exception.DuplicatePlayerPerformanceException;
import com.example.projeto_spring.repository.PerformanceRepository;
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
class ValidatePlayerAppearancesPerMatchTest {
    @InjectMocks
    ValidatePlayerAppearancesPerMatch validator;
    @Mock
    PerformanceRepository performanceRepository;
    public RegisterPerformanceDto dto;
    public UUID matchId = UUID.fromString("123e4567-e89b-12d3-a456-426614174000");
    public UUID playerId = UUID.fromString("6b14a96d-c516-448f-adb2-170e16f8c760");

    @BeforeEach
    public void setUp() {
        dto = new RegisterPerformanceDto(matchId, playerId, 8.0);
    }

    @Test
    void validate_shouldThrowException_whenPlayerAlreadyHasAPerfomance() {
        when(performanceRepository.existsByPlayerIdAndMatchId(dto.playerId(), dto.matchId())).thenReturn(true);
        assertThrows(DuplicatePlayerPerformanceException.class, () -> validator.validate(dto));
    }

    @Test
    void validate_shouldNotThrowException_whenPlayerAlreadyHasNoPerfomance() {
        when(performanceRepository.existsByPlayerIdAndMatchId(dto.playerId(), dto.matchId())).thenReturn(false);
        assertDoesNotThrow(() -> validator.validate(dto));
    }
}