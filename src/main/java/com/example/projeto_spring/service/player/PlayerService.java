package com.example.projeto_spring.service.player;

import com.example.projeto_spring.domain.*;
import com.example.projeto_spring.dto.player.UpdatePlayerDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.dto.mapper.PlayerMapper;
import com.example.projeto_spring.dto.transfer.RegisterTransferDto;
import com.example.projeto_spring.enums.TransferType;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.TransferRepository;
import com.example.projeto_spring.service.player.validations.PlayerValidator;
import com.example.projeto_spring.service.transfer.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlayerService {

    @Autowired
    private PlayerMapper playerMapper;

    @Autowired
    private List<PlayerValidator> validators;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TransferRepository transferRepository;


    @Autowired
    private TransferService transferService;

    public Player register(RegisterPlayerDto dto) {
        validators.forEach(v -> v.validate(dto));
        Player player = playerMapper.toEntity(dto);

        Team team = teamRepository.getReferenceById(dto.teamId());
        player.setTeam(team);

        Nationality nationality = nationalityRepository.getReferenceById(dto.nationalityId());
        player.setNationality(nationality);

        playerRepository.save(player);

//        RegisterTransferDto transferRegisterDto = new TransferRegisterDto(player.getId(), player.getTeam().getId(), player.getContract().getPaidValue() == 0 ? 0 : player.getContract().getPaidValue() * -1, player.getContract().getContractStart(), TransferType.PURCHASE);
//        transferService.purchase(transferRegisterDto);

        return player;
    }

    public List<Player> list() {
        return playerRepository.findAll();
    }

    public Player listPlayerById(UUID id) {
        return playerRepository.getReferenceById(id);
    }

    public List<Player> listPlayerByTeamId(UUID timeId) {
        return playerRepository.findByTeamId(timeId);
    }

    public Player update(UpdatePlayerDto dto, UUID id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        Player player = playerOpt.orElseThrow();

        if (dto.name() != null) {
            player.setName(dto.name());
        }
        if (dto.nationalityId() != null) {
            Nationality nationality = nationalityRepository.getReferenceById(dto.nationalityId());
            player.setNationality(nationality);
        }
        if (dto.shirtNumber() != null) {
            player.setShirtNumber(dto.shirtNumber());
        }
        if (dto.height() != null) {
            player.setHeight(dto.height());
        }
        if (dto.position() != null) {
            player.setPosition(dto.position());
        }
        if (dto.contract() != null && player.getContract() != null) {
            player.getContract().updateContract(dto.contract());
        }
        return player;
    }

    public void delete(UUID id) {
        Player player = playerRepository.getReferenceById(id);
//        TransferRegisterDto dto = new TransferRegisterDto(player.getId(), player.getTeam().getId(), player.getContract().getCurrentValue(), LocalDate.now(), TransferType.SALE);
        playerRepository.deleteById(player.getId());
//        transferService.sell(dto);
    }
}