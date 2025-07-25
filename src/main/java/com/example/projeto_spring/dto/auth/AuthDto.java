package com.example.projeto_spring.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthDto(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
