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
    @ManyToOne
    @JoinColumn(name = "time_id")
    private Time time;
    @ManyToOne
    @JoinColumn(name = "nacionalidade_id")
    private Nacionalidade nacionalidade;
    private String nome;
    private String nomeCompleto;
    @Enumerated(EnumType.STRING)
    private Posicao posicao;
    private Double altura;
    private int numeroCamisa;
    private LocalDate dataNascimento;
    @Embedded
    private Contrato contrato;
}