package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Partida;
import com.example.projeto_spring.dto.partida.DtoCadastroPartida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PartidaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", ignore = true)
    Partida toEntity(DtoCadastroPartida dtoCadastroPartida);
}
