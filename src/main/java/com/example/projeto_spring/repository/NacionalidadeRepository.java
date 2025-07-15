package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NacionalidadeRepository extends JpaRepository<Nacionalidade, Long> {
    boolean existsByNome(String nome);
}
