package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Performance;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PerformanceRepository extends JpaRepository<Performance, UUID> {
    List<Performance> findByPlayerId(UUID playerId);

    @Query(value = """
                SELECT p.* FROM performances p
                JOIN matches m ON p.match_id = m.id
                WHERE m.team_id = :teamId
            """, nativeQuery = true)
    List<Performance> findByTeamId(UUID teamId);

    Boolean existsByPlayerIdAndMatchId(@NotNull UUID playerId, @NotNull UUID matchId);
}
