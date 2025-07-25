package com.example.projeto_spring.domain;

import com.example.projeto_spring.enums.TransferType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "transfers")
@Entity(name = "Transfer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    private Double value;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TransferType transferType;
}
