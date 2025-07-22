package com.example.projeto_spring.service.cadastro;

import com.example.projeto_spring.domain.Usuario;
import com.example.projeto_spring.dto.autenticacao.DtoAutenticacao;
import com.example.projeto_spring.repository.UsuarioRepository;
import com.example.projeto_spring.service.cadastro.validacoes.ValidadorCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorCadastro> validadores;

    public void validar(DtoAutenticacao dto) {
         validadores.forEach(v -> v.validar(dto));
        var senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario usuario = new Usuario(dto.login(), senhaCriptografada);
        usuarioRepository.save(usuario);
    }
}
