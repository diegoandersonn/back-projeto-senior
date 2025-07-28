package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransferRepository extends JpaRepository<Transfer, UUID> {
    List<Transfer> findByTeamId(UUID teamId);
}
