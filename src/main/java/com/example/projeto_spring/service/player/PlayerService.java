package com.example.projeto_spring.service.player;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.mapper.PlayerMapper;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.dto.player.UpdatePlayerDto;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.PlayerRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.service.player.validations.PlayerValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
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

    public Player register(RegisterPlayerDto dto) {
        validators.forEach(v -> v.validate(dto));
        Player player = playerMapper.toEntity(dto);

        Team team = teamRepository.getReferenceById(dto.teamId());
        player.setTeam(team);

        Nationality nationality = nationalityRepository.getReferenceById(dto.nationalityId());
        player.setNationality(nationality);

        playerRepository.save(player);
        return player;
    }

    public List<Player> list(String sort) {
        if ("position".equalsIgnoreCase(sort)) {
            return playerRepository.findAllByOrderByPositionAsc();
        } else {
            return playerRepository.findAll();
        }
    }

    public Player listPlayerById(UUID id) {
        return playerRepository.getReferenceById(id);
    }

    public List<Player> listPlayerByTeamId(UUID teamId, String sort) {
        if ("position".equalsIgnoreCase(sort)) {
            List<Player> players = playerRepository.findByTeamIdOrderByPositionAsc(teamId);
            players.sort(Comparator.comparingInt(p -> p.getPosition().ordinal()));
            return players;
        } else {
            return playerRepository.findByTeamId(teamId);
        }
    }

    public Player update(UpdatePlayerDto dto, UUID id) {
        Player player = playerRepository.getReferenceById(id);

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
        playerRepository.deleteById(id);
    }
}