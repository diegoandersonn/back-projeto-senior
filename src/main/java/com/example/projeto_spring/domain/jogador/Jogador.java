package com.example.projeto_spring.domain.jogador;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "jogadores")
@Entity(name = "Jogador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long timeId;
    private String nome;
    private String nomeCompleto;
    @Enumerated(EnumType.STRING)
    private Posicao posicao;
    private Double altura;
    private Double salario;
    private int numeroCamisa;
    private Long nacionalidadeId;
    private Double valorAtual;
    private Double valorPago;
    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato;
    private LocalDate dataNascimento;
    private LocalDate contratoInicio;
    private LocalDate contratoFim;

    public Jogador(DtoCadastroJogador dto) {
        this.nome = dto.nome();
        this.nomeCompleto = dto.nomeCompleto();
        this.altura = dto.altura();
        this.numeroCamisa = dto.numeroCamisa();
        this.posicao = dto.posicao();
        this.nacionalidadeId = dto.nacionalidadeId();
        this.timeId = dto.timeId();
        this.salario = dto.salario();
        this.valorAtual = dto.valorAtual();
        this.valorPago = dto.valorPago();
        this.tipoContrato = dto.tipoContrato();
        this.dataNascimento = dto.dataNascimento();
        this.contratoInicio = dto.contratoInicio();
        this.contratoFim = dto.contratoFim();
    }
}