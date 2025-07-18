package com.example.projeto_spring.repository;

import com.example.projeto_spring.domain.Usuario;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByLogin(String login);

    boolean existsByLogin(@NotBlank String login);
}
