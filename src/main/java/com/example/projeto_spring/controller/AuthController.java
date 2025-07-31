package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.User;
import com.example.projeto_spring.dto.security.JwtTokenDto;
import com.example.projeto_spring.dto.auth.AuthDto;
import com.example.projeto_spring.repository.UserRepository;
import com.example.projeto_spring.service.JwtTokenService;
import com.example.projeto_spring.service.register.RegisterService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterService validaCadastro;

    @Autowired
    private UserRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody @Valid AuthDto dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.gerarToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new JwtTokenDto(jwtToken));
    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity registerUser(@RequestBody @Valid AuthDto dto) {
        validaCadastro.validar(dto);
        return ResponseEntity.ok().build();
    }
}
