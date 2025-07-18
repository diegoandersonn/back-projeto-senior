package com.example.projeto_spring.validators.cadastro.validacoes;

import com.example.projeto_spring.dto.autenticacao.DtoAutenticacao;

public interface ValidadorCadastro {
    void validar(DtoAutenticacao dto);
}
