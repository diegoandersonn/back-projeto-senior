package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.usuario.Usuario;
import com.example.projeto_spring.dto.security.DtoTokenJWT;
import com.example.projeto_spring.dto.usuario.DtoAutenticacao;
import com.example.projeto_spring.repository.UsuarioRepository;
import com.example.projeto_spring.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        var senhaCriptografada = passwordEncoder.encode(dto.senha());
        Usuario usuario = new Usuario(dto.login(), senhaCriptografada);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }
}
