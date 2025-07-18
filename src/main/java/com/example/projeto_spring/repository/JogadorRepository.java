package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Jogador;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findByTimeId(Long timeId);

    Boolean existsByTimeIdAndNumeroCamisa(Long timeId, int numeroCamisa);

    Jogador findByTimeIdAndNumeroCamisa(Long timeId, int numeroCamisa);
}
