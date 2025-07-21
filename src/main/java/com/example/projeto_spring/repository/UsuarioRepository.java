package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    UserDetails findByLogin(String login);

    boolean existsByLogin(@NotBlank String login);
}
