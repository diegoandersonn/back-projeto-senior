package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
}
