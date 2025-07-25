package com.example.projeto_spring.service.transfer;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.dto.mapper.TransferMapper;
import com.example.projeto_spring.dto.transfer.TransferRegisterDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Transfer purchase(TransferRegisterDto dto) {
        Transfer transfer = setTransfer(dto, TransferType.PURCHASE);
        transferRepository.save(transfer);
        return transfer;
    }

    public Transfer sell(TransferRegisterDto dto) {
        Transfer transfer = setTransfer(dto, TransferType.SALE);
        transferRepository.save(transfer);
        return transfer;
    }

    public Transfer setTransfer(TransferRegisterDto dto, TransferType transferType) {
        Transfer transfer = transferMapper.toEntity(dto);

        Player player = playerRepository.getReferenceById(dto.playerId());
        transfer.setPlayer(player);

        Team team = teamRepository.getReferenceById(dto.teamId());
        transfer.setTeam(team);

        transfer.setTransferType(transferType);
        return  transfer;
    }
}
