package com.example.projeto_spring.controller;

import com.example.projeto_spring.repository.AtuacaoRepository;
import com.example.projeto_spring.repository.PartidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("partidas")
public class PartidaController {

    @Autowired
    private PartidaRepository partidaRepository;

}
