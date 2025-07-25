package com.example.projeto_spring.dto.nacionalidade;

import com.example.projeto_spring.domain.Nationality;

import java.util.UUID;

public record DetailNationalityDto(UUID id, String name, String flagImage, String acronym, String continent) {
    public DetailNationalityDto(Nationality nationality) {
        this(nationality.getId(), nationality.getName(), nationality.getFlagImage(), nationality.getAcronym(), nationality.getContinent());
    }
}
