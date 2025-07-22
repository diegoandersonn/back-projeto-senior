package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PartidaRepository extends JpaRepository<Partida, UUID> {
}
