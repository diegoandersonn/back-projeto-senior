package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.contrato.DtoAtualizarContrato;
import com.example.projeto_spring.dto.contrato.DtoCadastroContrato;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Embeddable
@Getter
@AllArgsConstructor
public class Contrato {
    private Double salario;
    private Double valorAtual;
    private Double valorPago;
    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;
    private LocalDate contratoInicio;
    private LocalDate contratoFim;

    public Contrato() {
    }

    public Contrato(DtoCadastroContrato dto) {
        this.salario = dto.salario();
        this.valorAtual = dto.valorAtual();
        this.valorPago = dto.valorPago();
        this.tipoContrato = dto.tipoContrato();
        this.contratoInicio = dto.contratoInicio();
        this.contratoFim = dto.contratoFim();
    }

    public void atualizarContrato(DtoAtualizarContrato dto) {
        if (dto.salario() != null) {
            this.salario = dto.salario();
        }
        if (dto.valorAtual() != null) {
            this.valorAtual = dto.valorAtual();
        }
        if (dto.tipoContrato() != null) {
            this.tipoContrato = dto.tipoContrato();
        }
        if (dto.contratoFim() != null) {
            this.contratoFim = dto.contratoFim();
        }
    }
}
