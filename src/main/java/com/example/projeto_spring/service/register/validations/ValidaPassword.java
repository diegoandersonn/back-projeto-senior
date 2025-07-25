package com.example.projeto_spring.service.register.validations;

import com.example.projeto_spring.dto.auth.AuthDto;
import com.example.projeto_spring.infra.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidaPassword implements RegisterValidator {
    public void validate(AuthDto dto) {
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)[^@\\.;\\(\\)\\_!?&\\-\\+\\^\\´âêôáéíóúÂÊÔÁÉÍÓÚ]+$");
        Matcher matcher = pattern.matcher(dto.password());

        if (dto.password().length() < 8 || dto.password().length() > 32) {
            throw new ValidacaoException("Senha deve conter de 8 a 32 caracteres!");
        }

        //deve ter, no mínimo, uma letra maiúscula, uma letra minúscula e um número;
        //não pode ter caractere de pontuação, acentuação ou espaço
        if (!matcher.find()) {
            throw new ValidacaoException("Senha inválida!");
        }
    }
}
