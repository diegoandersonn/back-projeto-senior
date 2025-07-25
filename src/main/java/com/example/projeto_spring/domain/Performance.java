package com.example.projeto_spring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "performances")
@Entity(name = "Performance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "partida_id")
    private Match match;
    @ManyToOne
    @JoinColumn(name = "jogador_id")
    private Player player;
    private Double rating;
}
