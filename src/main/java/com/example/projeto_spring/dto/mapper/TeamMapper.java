package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Team;
import com.example.projeto_spring.dto.team.TeamRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    Team toEntity(TeamRegisterDto teamRegisterDto);
}
