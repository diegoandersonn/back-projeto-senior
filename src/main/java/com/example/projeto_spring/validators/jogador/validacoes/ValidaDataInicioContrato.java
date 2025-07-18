package com.example.projeto_spring.validators.jogador.validacoes;

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
        int ano = dto.contratoInicio().getYear();
        Boolean primeira, segunda;
        System.out.println(nacionalidade.getContinente());
        if (dto.valorPago() == 0) {
            return;
        } else {
            if (nacionalidade.getContinente().equals("America do Sul")) {
                primeira = !dto.contratoInicio().isBefore(LocalDate.of(ano, 1, 1)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 4, 16));
                segunda = !dto.contratoInicio().isBefore(LocalDate.of(ano, 6, 22)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 7, 21));
            } else if (nacionalidade.getContinente().equals("Europa")) {
                primeira = !dto.contratoInicio().isBefore(LocalDate.of(ano, 1, 1)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 2, 2));
                segunda = !dto.contratoInicio().isBefore(LocalDate.of(ano, 7, 1)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 8, 31));
            } else {
                primeira = !dto.contratoInicio().isBefore(LocalDate.of(ano, 1, 1)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 1, 31));
                segunda = !dto.contratoInicio().isBefore(LocalDate.of(ano, 7, 1)) && !dto.contratoInicio().isAfter(LocalDate.of(ano, 8, 31));
            }

        }

        if (!primeira && !segunda) {
            throw new RuntimeException("Data fora das janela de transferência");
        }
    }
}
