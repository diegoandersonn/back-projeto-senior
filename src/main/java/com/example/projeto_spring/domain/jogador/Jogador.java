package com.example.projeto_spring.domain.jogador;

import com.example.projeto_spring.dto.jogador.DtoAtualizarJogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.enums.Posicao;
import com.example.projeto_spring.enums.TipoContrato;
import jakarta.persistence.*;
import jakarta.validation.Valid;
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

    public void atualizar(@Valid DtoAtualizarJogador dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.numeroCamisa() != null) {
            this.numeroCamisa = dto.numeroCamisa();
        }
        if (dto.altura() != null) {
            this.altura = dto.altura();
        }
        if (dto.posicao() != null) {
            this.posicao = dto.posicao();
        }
        if (dto.nacionalidadeId() != null) {
            this.nacionalidadeId = dto.nacionalidadeId();
        }
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