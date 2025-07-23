package com.example.projeto_spring.dto.transferencia;

import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.contrato.DtoListagemContrato;
import com.example.projeto_spring.enums.TipoTransferencia;

import java.time.LocalDate;
import java.util.UUID;

public record DtoListagemTransferencia(UUID id, UUID timeId, UUID jogadorId, Double valor, LocalDate data, TipoTransferencia tipoTransferencia) {
    public DtoListagemTransferencia(Transferencia transferencia) {
        this(transferencia.getId(), transferencia.getTime().getId(), transferencia.getJogador().getId(), transferencia.getValor(), transferencia.getData(), transferencia.getTipoTransferencia());
    }
}
