package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.jogador.DtoAtualizarJogador;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import com.example.projeto_spring.enums.Posicao;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "jogadores")
@Entity(name = "Jogador")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID timeId;
    private String nome;
    private String nomeCompleto;
    @Enumerated(EnumType.STRING)
    private Posicao posicao;
    private Double altura;
    private int numeroCamisa;
    private UUID nacionalidadeId;
    private LocalDate dataNascimento;
    @Embedded
    private Contrato contrato;

    public Jogador(DtoCadastroJogador dto) {
        this.nome = dto.nome();
        this.nomeCompleto = dto.nomeCompleto();
        this.altura = dto.altura();
        this.numeroCamisa = dto.numeroCamisa();
        this.posicao = dto.posicao();
        this.nacionalidadeId = dto.nacionalidadeId();
        this.timeId = dto.timeId();
        this.dataNascimento = dto.dataNascimento();
        this.contrato = new Contrato(dto.contrato());
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
    }

    public void excluir() {
//        this.timeId = /;
    }
}