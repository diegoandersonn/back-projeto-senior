package com.example.projeto_spring.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DtoAutenticacao(
        @NotBlank
        String login,
        @NotBlank
        String senha
) {
}
