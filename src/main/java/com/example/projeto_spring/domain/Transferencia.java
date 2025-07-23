package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import com.example.projeto_spring.enums.TipoTransferencia;
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
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;
    private Double valor;
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private TipoTransferencia tipoTransferencia;
}
