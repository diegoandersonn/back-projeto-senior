package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Time;
import com.example.projeto_spring.dto.time.DtoCadastroTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "nacionalidade", ignore = true)
    Time toEntity(DtoCadastroTime dtoCadastroTime);
}
