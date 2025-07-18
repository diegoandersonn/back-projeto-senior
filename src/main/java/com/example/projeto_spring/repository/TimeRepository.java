package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Time;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeRepository extends JpaRepository<Time, Long> {
}
