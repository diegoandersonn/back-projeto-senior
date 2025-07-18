package com.example.projeto_spring.controller;

import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("transferencias")
public class TransferenciaController {
    @Autowired
    TransferenciaRepository transferenciaRepository;

    @GetMapping
    public ResponseEntity listar() {
        List<Transferencia> transferencias = transferenciaRepository.findAll();
        return ResponseEntity.ok(transferencias);
    }
}
