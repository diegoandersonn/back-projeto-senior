package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.jogador.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
}
