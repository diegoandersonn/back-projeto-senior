package com.example.projeto_spring.service.transfer;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.mapper.TransferMapper;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.TransferRepository;
import com.example.projeto_spring.service.transfer.validations.TransferValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TransferServiceTest {

    @InjectMocks
    public TransferService service;

    @Mock
    public TransferValidator transferValidator1;

    @Mock
    public TransferValidator transferValidator2;

    @Mock
    public TransferValidator transferValidator3;

    @Mock
    public TransferValidator transferValidator4;

    @Mock
    public TransferMapper transferMapper;

    @Mock
    public TransferRepository transferRepository;

    @Mock
    public PlayerRepository playerRepository;

    @Mock
    public TeamRepository teamRepository;

    public RegisterTransferDto dto;
    public Team team;
    public UUID teamId = UUID.randomUUID();
    public Nationality nationality;

    @BeforeEach
    void setUp() {
        service = new TransferService(List.of(transferValidator1, transferValidator2, transferValidator3, transferValidator4), );
        dto = new RegisterTransferDto(UUID.randomUUID(), teamId, 100000.00, LocalDate.now(), TransferType.PURCHASE);
        team = new Team();
        nationality = new Nationality();
    }

    void test() {
        
    }
}