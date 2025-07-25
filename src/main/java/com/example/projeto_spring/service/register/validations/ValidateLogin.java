package com.example.projeto_spring.service.register.validations;

import com.example.projeto_spring.dto.auth.AuthDto;
import com.example.projeto_spring.infra.exception.ValidacaoException;
import com.example.projeto_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateLogin implements RegisterValidator {

    @Autowired
    private UserRepository usuarioRepository;

    public void validate(AuthDto dto) {
        if (usuarioRepository.existsByLogin(dto.login())) {
            throw new ValidacaoException("Login inválido!");
        }
    }
}
