package com.example.projeto_spring.service;

import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.dto.nacionalidade.DtoApiNacionalidade;
import com.example.projeto_spring.dto.nacionalidade.DtoCadastroNacionalidade;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiNacionalidadesService {
    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Transactional
    public void importarNacionalidades() {
        String url = "https://restcountries.com/v3.1/all?fields=name,flags,cca3,region";
        ResponseEntity<DtoApiNacionalidade[]> response = restTemplate.getForEntity(url, DtoApiNacionalidade[].class);
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            for (DtoApiNacionalidade pais : response.getBody()) {
                String nome = pais.name.common;
                String imagem = pais.flags.png;
                String sigla = pais.cca3;
                String continente = pais.region;

                if (!nacionalidadeRepository.existsByNome(nome)) {
                    DtoCadastroNacionalidade dto = new DtoCadastroNacionalidade(nome, imagem, sigla, continente);
                    Nacionalidade nacionalidade = new Nacionalidade(dto);
                    nacionalidadeRepository.save(nacionalidade);
                }
            }
        }
    }
}
