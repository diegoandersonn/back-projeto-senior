package com.example.projeto_spring.domain;

import com.example.projeto_spring.enums.Position;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "players")
@Entity(name = "Player")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;
    private String name;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Position position;
    private Double height;
    private int shirtNumber;
    private LocalDate birthDate;
    @Embedded
    private Contract contract;
}