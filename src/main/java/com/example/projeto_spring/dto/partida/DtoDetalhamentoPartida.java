package com.example.projeto_spring.dto.partida;

import com.example.projeto_spring.domain.Partida;

import java.time.LocalDate;
import java.util.UUID;

public record DtoDetalhamentoPartida(UUID id, UUID timeId, LocalDate data) {
    public DtoDetalhamentoPartida(Partida partida) {
        this(partida.getId(), partida.getTime().getId(), partida.getData());
    }
}
