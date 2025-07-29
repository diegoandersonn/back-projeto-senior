package com.example.projeto_spring.service.match.validations;

import com.example.projeto_spring.dto.match.RegisterMatchDto;
import com.example.projeto_spring.infra.exception.MatchOnSameDateException;
import com.example.projeto_spring.repository.MatchRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidateMatchOnSameDateTest {
    @InjectMocks
    public ValidateMatchOnSameDate validator;

    @Mock
    public MatchRepository matchRepository;

    public RegisterMatchDto registerMatchDto;

    @BeforeEach
    public void setUp() {
        registerMatchDto = new RegisterMatchDto(UUID.fromString("123e4567-e89b-12d3-a456-426614174000"), LocalDate.now());
    }

    @Test
    void validate_shouldThrowException_whenTeamHasAMatchOnTheSameDate(){
        when(matchRepository.existsByDateAndTeamId(any(), any())).thenReturn(true);
        assertThrows(MatchOnSameDateException.class, () -> validator.validate(registerMatchDto));
    }
    @Test
    void validate_shouldNotThrowException_whenTeamDoesNotHaveAMatchOnTheSameDate(){
        when(matchRepository.existsByDateAndTeamId(any(), any())).thenReturn(false);
        assertDoesNotThrow(() -> validator.validate(registerMatchDto));
    }
}