package com.example.projeto_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "atuacoes")
@Entity(name = "Atuacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Atuacao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Partida partida;
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Jogador jogador;
    private Double nota;
}
