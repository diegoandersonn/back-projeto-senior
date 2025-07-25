package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NationalityRepository extends JpaRepository<Nationality, UUID> {
    boolean existsByName(String name);
}
