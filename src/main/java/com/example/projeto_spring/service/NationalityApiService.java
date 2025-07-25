package com.example.projeto_spring.service;

import com.example.projeto_spring.domain.Nationality;
import com.example.projeto_spring.dto.nacionalidade.NationalityApiDto;
import com.example.projeto_spring.dto.nacionalidade.RegisterNationalityDto;
import com.example.projeto_spring.repository.NationalityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NationalityApiService {
    @Autowired
    private NationalityRepository nationalityRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void importNationalities() {
        String url = "https://restcountries.com/v3.1/all?fields=name,flags,cca3,region";
        ResponseEntity<NationalityApiDto[]> response = restTemplate.getForEntity(url, NationalityApiDto[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            for (NationalityApiDto pais : response.getBody()) {
                String nome = pais.name.common;
                String imagem = pais.flags.png;
                String sigla = pais.cca3;
                String continente = pais.region;

                if (!nationalityRepository.existsByName(nome)) {
                    RegisterNationalityDto dto = new RegisterNationalityDto(nome, imagem, sigla, continente);
                    Nationality nacionalidade = new Nationality(dto);
                    nationalityRepository.save(nacionalidade);
                }
            }
        }
    }
}
