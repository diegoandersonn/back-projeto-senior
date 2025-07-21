package com.example.projeto_spring.dto.time;

import com.example.projeto_spring.domain.Time;

import java.util.UUID;

public record DtoListarTime(UUID id, String nome, String estadio, Double saldoTransferencias, UUID nacionalidadeId) {
    public DtoListarTime(Time time) {
        this(time.getId(), time.getNome(), time.getEstadio(), time.getSaldoTransferencias(), time.getNacionalidadeId());
    }
}
