package com.example.projeto_spring.domain;

import com.example.projeto_spring.dto.nacionalidade.RegisterNationalityDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "nationalities")
@Entity(name = "Nationality")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nationality {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(name = "image")
    private String flagImage;
    private String acronym;
    private String continent;

    public Nationality(RegisterNationalityDto dto) {
        this.name = dto.name();
        this.flagImage = dto.flagImage();
        this.acronym = dto.acronym();
        this.continent = dto.continent();
    }
}
