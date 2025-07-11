package com.example.projeto_spring.domain.nacionalidade;

import jakarta.validation.constraints.NotBlank;

public record DtoCadastroNacionalidade(
        @NotBlank
        String nome,
        @NotBlank
        String imagemBandeira,
        @NotBlank
        String sigla
) {}
