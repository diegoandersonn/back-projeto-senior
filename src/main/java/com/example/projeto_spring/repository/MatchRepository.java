package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Match;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MatchRepository extends JpaRepository<Match, UUID> {
    List<Match> findMatchByTeamId(UUID teamId);

    boolean existsByTeamIdAndId(UUID teamId, UUID Id);

    boolean existsByDateAndTeamId(@NotNull @PastOrPresent LocalDate date, @NotNull UUID uuid);
}

