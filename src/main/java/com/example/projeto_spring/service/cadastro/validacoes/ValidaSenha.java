package com.example.projeto_spring.service.cadastro.validacoes;

import com.example.projeto_spring.dto.autenticacao.DtoAutenticacao;
import com.example.projeto_spring.infra.exception.ValidacaoExpection;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidaSenha implements ValidadorCadastro {
    public void validar(DtoAutenticacao dto) {
        Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$)[^@\\.;\\(\\)\\_!?&\\-\\+\\^\\´âêôáéíóúÂÊÔÁÉÍÓÚ]+$");
        Matcher matcher = pattern.matcher(dto.senha());

        if (dto.senha().length() < 8 || dto.senha().length() > 32) {
            throw new ValidacaoExpection("Senha deve conter de 8 a 32 caracteres!");
        }

        //deve ter, no mínimo, uma letra maiúscula, uma letra minúscula e um número;
        //não pode ter caractere de pontuação, acentuação ou espaço
        if (!matcher.find()) {
            throw new ValidacaoExpection("Senha inválida!");
        }


    }
}
