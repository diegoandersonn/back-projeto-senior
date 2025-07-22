package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Usuario;
import com.example.projeto_spring.dto.security.DtoTokenJWT;
import com.example.projeto_spring.dto.autenticacao.DtoAutenticacao;
import com.example.projeto_spring.repository.UsuarioRepository;
import com.example.projeto_spring.service.TokenJWTService;
import com.example.projeto_spring.service.cadastro.CadastroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private TokenJWTService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CadastroService validaCadastro;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity efetuarLogin(@RequestBody @Valid DtoAutenticacao dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.senha());
        var auth = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.gerarToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new DtoTokenJWT(jwtToken));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity cadastar(@RequestBody @Valid DtoAutenticacao dto) {
        validaCadastro.validar(dto);
        return ResponseEntity.ok().build();
    }
}
