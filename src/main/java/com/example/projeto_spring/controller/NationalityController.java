package com.example.projeto_spring.controller;

import com.example.projeto_spring.dto.nacionalidade.DetailNationalityDto;
import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.dto.nacionalidade.RegisterNationalityDto;
import com.example.projeto_spring.repository.NationalityRepository;
import com.example.projeto_spring.service.NationalityApiService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("nationalities")
public class NationalityController {
    @Autowired
    NationalityRepository nationalityRepository;
    @Autowired
    private NationalityApiService service;

    @PostMapping
    @Transactional
    public ResponseEntity registerNationality(@RequestBody @Valid RegisterNationalityDto dto, UriComponentsBuilder uriBuilder) {
        Nationality nationality = new Nationality(dto);
        nationalityRepository.save(nationality);
        var uri = uriBuilder.path("nationalities/{id}").buildAndExpand(nationality.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailNationalityDto(nationality));
    }

    @PostMapping("/import")
    public ResponseEntity importNationalities() {
        service.importNationalities();
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity listNationalities() {
        List<Nationality> nationalities = nationalityRepository.findAll();
        return ResponseEntity.ok(nationalities);
    }
}
