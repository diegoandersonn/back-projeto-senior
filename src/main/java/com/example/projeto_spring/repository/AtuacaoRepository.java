package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Atuacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtuacaoRepository extends JpaRepository<Atuacao, UUID> {
}
