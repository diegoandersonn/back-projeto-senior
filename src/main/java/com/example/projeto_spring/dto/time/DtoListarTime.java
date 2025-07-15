package com.example.projeto_spring.dto.time;

import com.example.projeto_spring.domain.time.Time;

public record DtoListarTime(Long id, String nome, String estadio, Double saldoTransferencias, Long nacionalidadeId) {
    public DtoListarTime(Time time) {
        this(time.getId(), time.getNome(), time.getEstadio(), time.getSaldoTransferencias(), time.getNacionalidadeId());
    }
}
