package com.example.projeto_spring.service.cadastro.validacoes;

import com.example.projeto_spring.dto.autenticacao.DtoAutenticacao;
import com.example.projeto_spring.infra.exception.ValidacaoException;
import com.example.projeto_spring.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaLogin implements ValidadorCadastro {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validar(DtoAutenticacao dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new ValidacaoException("Login inválido!");
        }
    }
}
