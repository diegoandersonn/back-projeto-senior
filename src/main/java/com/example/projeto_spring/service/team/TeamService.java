package com.example.projeto_spring.service.team;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.domain.User;
import com.example.projeto_spring.dto.mapper.TeamMapper;
import com.example.projeto_spring.dto.team.RegisterTeamDto;
import com.example.projeto_spring.dto.team.UpdateTeamDto;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.repository.TeamRepository;
import com.example.projeto_spring.repository.UserRepository;
import com.example.projeto_spring.service.team.validations.TeamValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TeamService {

    @Autowired
    private List<TeamValidator> validators;

    @Autowired
    private TeamMapper teamMapper;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private UserRepository userRepository;

    public Team register(RegisterTeamDto dto) {
        validators.forEach(v -> v.validate(dto));
        Team team = teamMapper.toEntity(dto);

        User user = userRepository.getReferenceById(dto.userId());
        team.setUser(user);

        Nationality nationality = nationalityRepository.getReferenceById(dto.nationalityId());
        team.setNationality(nationality);

        teamRepository.save(team);
        return team;
    }

    public List<Team> list() {
        return teamRepository.findAll();
    }

    public Team listTeamById(UUID timeId) {
        return teamRepository.getReferenceById(timeId);
    }

    public List<Team> listTeamByUserId(UUID usuarioId) {
        return teamRepository.findAllByUserId(usuarioId);
    }

    public Team update(UpdateTeamDto dto, UUID timeId) {
        Team team = teamRepository.getReferenceById(timeId);

        if (dto.name() != null) {
            team.setName(dto.name());
        }
        if (dto.stadium() != null) {
            team.setStadium(dto.stadium());
        }
        if (dto.transferBalance() != null) {
            team.setTransferBalance(dto.transferBalance());
        }
        return team;
    }

    public void delete(UUID id) {
        teamRepository.deleteById(id);
    }
}

