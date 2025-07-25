package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.team.TeamUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.UUID;

@Table(name = "teams")
@Entity(name = "Team")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String stadium;
    private Double transferBalance;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "nationality_id")
    private Nationality nationality;

    public void atualizar(@Valid TeamUpdateDto dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.stadium() != null) {
            this.stadium = dto.stadium();
        }
        if (dto.transferBalance() != null) {
            this.transferBalance = dto.transferBalance();
        }
    }
}
