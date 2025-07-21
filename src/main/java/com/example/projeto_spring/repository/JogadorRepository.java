package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Jogador;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JogadorRepository extends JpaRepository<Jogador, UUID> {
    List<Jogador> findByTimeId(UUID timeId);

    Boolean existsByTimeIdAndNumeroCamisa(UUID timeId, int numeroCamisa);

    Jogador findByTimeIdAndNumeroCamisa(UUID timeId, int numeroCamisa);
}
