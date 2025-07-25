package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Match;
import com.example.projeto_spring.dto.match.RegisterMatchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    Match toEntity(RegisterMatchDto matchRegisterDto);
}
