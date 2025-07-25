package com.example.projeto_spring.dto.nacionalidade;

import jakarta.validation.constraints.NotBlank;

public record RegisterNationalityDto(
        @NotBlank
        String name,
        @NotBlank
        String flagImage,
        @NotBlank
        String acronym,
        @NotBlank
        String continent
) {
}
