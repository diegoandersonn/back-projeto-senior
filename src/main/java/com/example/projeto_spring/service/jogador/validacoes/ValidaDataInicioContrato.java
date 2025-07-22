package com.example.projeto_spring.service.jogador.validacoes;

import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.repository.NacionalidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidaDataInicioContrato implements ValidadorJogador {

    @Autowired
    private NacionalidadeRepository nacionalidadeRepository;

    public void validar(DtoCadastroJogador dto) {
        Nacionalidade nacionalidade = nacionalidadeRepository.getReferenceById(dto.nacionalidadeId());
        var contrato = dto.contrato().contratoInicio();
        int ano = contrato.getYear();
        Boolean primeiraJanela, segundaJanela;

        if (dto.contrato().valorPago() == 0) {
            return;
        }

        if (nacionalidade.getContinente().equals("America do Sul")) {
            primeiraJanela = !contrato.isBefore(LocalDate.of(ano, 1, 1)) && !contrato.isAfter(LocalDate.of(ano, 4, 16));
            segundaJanela = !contrato.isBefore(LocalDate.of(ano, 6, 22)) && !contrato.isAfter(LocalDate.of(ano, 7, 21));
        } else if (nacionalidade.getContinente().equals("Europa")) {
            primeiraJanela = !contrato.isBefore(LocalDate.of(ano, 1, 1)) && !contrato.isAfter(LocalDate.of(ano, 2, 2));
            segundaJanela = !contrato.isBefore(LocalDate.of(ano, 7, 1)) && !contrato.isAfter(LocalDate.of(ano, 8, 31));
        } else {
            primeiraJanela = !contrato.isBefore(LocalDate.of(ano, 1, 1)) && !contrato.isAfter(LocalDate.of(ano, 1, 31));
            segundaJanela = !contrato.isBefore(LocalDate.of(ano, 7, 1)) && !contrato.isAfter(LocalDate.of(ano, 8, 31));
        }

        if (!primeiraJanela && !segundaJanela) {
            throw new RuntimeException("Data fora das janela de transferência");
        }
    }
}
