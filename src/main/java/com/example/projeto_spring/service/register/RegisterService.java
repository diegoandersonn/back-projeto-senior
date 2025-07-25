package com.example.projeto_spring.service.register;

import com.example.projeto_spring.domain.User;
import com.example.projeto_spring.dto.auth.AuthDto;
import com.example.projeto_spring.repository.UserRepository;
import com.example.projeto_spring.service.register.validations.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private List<RegisterValidator> validators;

    public void validar(AuthDto dto) {
        validators.forEach(v -> v.validate(dto));
        var cryptoPassword = passwordEncoder.encode(dto.password());
        User user = new User(dto.login(), cryptoPassword);
        userRepository.save(user);
    }
}
