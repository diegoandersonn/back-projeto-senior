package com.example.projeto_spring.dto.time;

import com.example.projeto_spring.domain.Nacionalidade;
import com.example.projeto_spring.domain.Time;

import java.util.UUID;

public record DtoListagemTime(UUID id, String nome, String estadio, Double saldoTransferencias, Nacionalidade nacionalidade) {
    public DtoListagemTime(Time time) {
        this(time.getId(), time.getNome(), time.getEstadio(), time.getSaldoTransferencias(), time.getNacionalidade());
    }
}
