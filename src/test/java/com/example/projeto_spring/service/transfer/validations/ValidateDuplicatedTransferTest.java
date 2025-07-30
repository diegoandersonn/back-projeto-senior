package com.example.projeto_spring.service.transfer.validations;

import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.infra.exception.DuplicatedTransferException;
import com.example.projeto_spring.repository.TransferRepository;
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
class ValidateDuplicatedTransferTest {

    @InjectMocks
    public ValidateDuplicatedTransfer validator;

    @Mock
    public TransferRepository transferRepository;

    public RegisterTransferDto dto;

    @BeforeEach
    void setUp() {
        dto = new RegisterTransferDto(UUID.randomUUID(), UUID.randomUUID(), 100000.00, LocalDate.now(), TransferType.PURCHASE);
    }

    @Test
    void shouldThrowException_WhenTransferAlreadyExistsForPlayerAndDate() {
        when(transferRepository.existsByPlayerIdAndDate(dto.playerId(), dto.date())).thenReturn(true);

        assertThrows(DuplicatedTransferException.class, () -> validator.validate(dto));
    }

    @Test
    void shouldNotThrowException_WhenNoTransferExistsForPlayerAndDate() {
        when(transferRepository.existsByPlayerIdAndDate(dto.playerId(), dto.date())).thenReturn(false);

        assertDoesNotThrow(() -> validator.validate(dto));
    }
}