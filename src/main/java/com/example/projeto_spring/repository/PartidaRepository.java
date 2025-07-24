package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Partida;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface PartidaRepository extends JpaRepository<Partida, UUID> {
    List<Partida> findPartidaByTimeId(UUID timeId);

    boolean existsByTimeIdAndId(UUID timeId, UUID Id);

    boolean existsByDataAndTimeId(@NotNull @PastOrPresent LocalDate data, @NotNull UUID uuid);
}
