package com.example.projeto_spring.service.player.validations;

import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import com.example.projeto_spring.infra.exception.InvalidShirtNumberException;
import com.example.projeto_spring.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateShirtNumber implements PlayerValidator {

    @Autowired
    PlayerRepository playerRepository;

    public void validate(RegisterPlayerDto dto) {
        if (playerRepository.existsByTeamIdAndShirtNumber(dto.teamId(), dto.shirtNumber())) {
            Player player = playerRepository.findByTeamIdAndShirtNumber(dto.teamId(), dto.shirtNumber());
            player.setShirtNumber(0);
        }

        if (dto.shirtNumber() < 1 || dto.shirtNumber() > 99) {
            throw new InvalidShirtNumberException("Número da camisa deve estar entre 1 e 99.");
        }
    }
}
