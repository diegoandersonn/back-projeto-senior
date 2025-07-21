package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TimeRepository extends JpaRepository<Time, UUID> {
    Optional<Time> findByUsuarioId(UUID usuarioId);
}
