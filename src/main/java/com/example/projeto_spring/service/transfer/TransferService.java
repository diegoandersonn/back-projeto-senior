package com.example.projeto_spring.service.transfer;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.dto.mapper.TransferMapper;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.dto.transfer.UpdateTransferDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.TransferRepository;
import com.example.projeto_spring.service.transfer.validations.TransferValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransferService {

    @Autowired
    private List<TransferValidator> validators;

    @Autowired
    private TransferMapper transferMapper;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public Transfer register(RegisterTransferDto dto) {
        validators.forEach(v -> v.validate(dto));

        Transfer transfer = transferMapper.toEntity(dto);

        Player player = playerRepository.getReferenceById(dto.playerId());
        transfer.setPlayer(player);

        Team team = teamRepository.getReferenceById(dto.teamId());
        transfer.setTeam(team);

        if (transfer.getTransferType() == TransferType.SALE) {
            player.setTeam(null);
        }

        transferRepository.save(transfer);
        return transfer;
    }

    public List<Transfer> list() {
        return transferRepository.findAll();
    }

    public List<Transfer> listByTeamId(UUID teamId) {
        return transferRepository.findByTeamId(teamId);
    }

    public Transfer update(UUID id, UpdateTransferDto dto) {
        Transfer transfer = transferRepository.getReferenceById(id);
        if (dto.value() != null) {
            transfer.setValue(dto.value());
        }
        if (dto.transferType() != null) {
            transfer.setTransferType(dto.transferType());
        }
        if (dto.date() != null) {
            transfer.setDate(dto.date());
        }
        return transfer;
    }

    public void delete(UUID id) {
        transferRepository.deleteById(id);
    }
}
