package com.example.projeto_spring.dto.nacionalidade;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoCadastroNacionalidade(
        @NotBlank
        String nome,
        @NotBlank
        String imagemBandeira,
        @NotBlank
        String sigla
) {
}
