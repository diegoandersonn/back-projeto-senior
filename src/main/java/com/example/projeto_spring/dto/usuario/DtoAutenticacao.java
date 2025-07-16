package com.example.projeto_spring.dto.usuario;

import jakarta.validation.constraints.NotBlank;

public record DtoAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
