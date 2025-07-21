package com.example.projeto_spring.dto.time;

import com.example.projeto_spring.domain.Time;

import java.util.UUID;

public record DtoDetalhamentoTime(UUID id, String nome, String estadio, Double saldoTransferencias) {
    public DtoDetalhamentoTime(Time time) {
        this(time.getId(), time.getNome(), time.getEstadio(), time.getSaldoTransferencias());
    }
}
