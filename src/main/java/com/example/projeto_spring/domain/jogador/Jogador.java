package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.domain.nacionalidade.Nacionalidade;
import com.example.projeto_spring.domain.time.Time;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "jogadores")
@Entity(name = "Jogador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "time_id")
    @Setter
    private Time time;
    private String nome;
    private int numeroCamisa;
    @Embedded
    private Nacionalidade nacionalidade;
    private Double valorAtual;
    private Double valorPago;
    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;
    private LocalDateTime dataNascimento;
    private LocalDateTime contratoInicio;
    private LocalDateTime contratoFim;

    public Jogador(DadosCadastroJogador dto) {
        this.nome = dto.nome();
        this.numeroCamisa = dto.numeroCamisa();
        this.nacionalidade = dto.nacionalidade();
        this.valorAtual = dto.valorAtual();
        this.valorPago = dto.valorPago();
        this.tipoContrato = dto.tipoContrato();
        this.dataNascimento = dto.dataNascimento();
        this.contratoInicio = dto.contratoInicio();
        this.contratoFim = dto.contratoFim();
    }
}
