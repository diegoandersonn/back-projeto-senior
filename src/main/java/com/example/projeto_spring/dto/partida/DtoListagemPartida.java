package com.example.projeto_spring.dto.partida;

import com.example.projeto_spring.domain.Partida;

import java.time.LocalDate;
import java.util.UUID;

public record DtoListagemPartida(UUID id, UUID timeId, LocalDate data) {
    public DtoListagemPartida(Partida partida) {
        this(partida.getId(), partida.getTime().getId(), partida.getData());
    }
}
