package com.example.projeto_spring.dto.contrato;

import com.example.projeto_spring.domain.Contrato;
import com.example.projeto_spring.enums.TipoContrato;

import java.time.LocalDate;

public record DtoListagemContrato(Double salario, Double valorAtual, Double valorPago, TipoContrato tipoContrato, LocalDate contratoInicio, LocalDate contratoFim) {
    public DtoListagemContrato(Contrato contrato) {
        this(contrato.getSalario(), contrato.getValorPago(), contrato.getValorPago(), contrato.getTipoContrato(), contrato.getContratoInicio(), contrato.getContratoFim());
    }
}
