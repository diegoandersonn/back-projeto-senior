package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Team;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TeamRepository extends JpaRepository<Team, UUID> {
    Optional<List<Team>> findAllByUserId(UUID userId);

    boolean existsByName(@NotBlank String name);
}
