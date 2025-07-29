package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Transfer;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    List<Transfer> findByTeamId(UUID teamId);

    boolean existsByPlayerIdAndDate(@NotNull UUID uuid, @NotNull @PastOrPresent LocalDate date);
}
