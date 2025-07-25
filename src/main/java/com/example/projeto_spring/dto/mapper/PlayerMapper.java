package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Contract;
import com.example.projeto_spring.domain.Player;
import com.example.projeto_spring.dto.contract.RegisterContractDto;
import com.example.projeto_spring.dto.player.RegisterPlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "nationality", ignore = true)
    @Mapping(target = "contract", expression = "java(mapContract(registerPlayerDto.contract()))")
    Player toEntity(RegisterPlayerDto registerPlayerDto);

    default Contract mapContract(RegisterContractDto dto) {
        if (dto == null) return null;
        return new Contract(dto);
    }
}

