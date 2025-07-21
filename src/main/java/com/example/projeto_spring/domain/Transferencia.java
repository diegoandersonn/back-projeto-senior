package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "transferencias")
@Entity(name = "Transferencia")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID jogadorId;
    private UUID timeId;
    private Double valor;
    private LocalDate data;

    public Transferencia(DtoCadastroTransferencia dto) {
        this.jogadorId = dto.jogadorId();
        this.timeId = dto.timeId();
        this.valor = dto.valor();
        this.data = dto.data();
    }
}
