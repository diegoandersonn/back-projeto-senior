package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID> {
    List<Player> findByTeamId(UUID teamId);

    Boolean existsByTeamIdAndShirtNumber(UUID teamId, int shirtNumber);

    Player findByTeamIdAndShirtNumber(UUID teamId, int shirtNumber);

    List<Player> findAllByTeamIdNotNull();

    List<Player> findAllByOrderByPositionAsc();

    List<Player> findByTeamIdOrderByPositionAsc(UUID teamId);
}
