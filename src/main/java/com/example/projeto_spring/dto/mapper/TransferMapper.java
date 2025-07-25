package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Transfer;
import com.example.projeto_spring.dto.transfer.TransferRegisterDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "player", ignore = true)
    @Mapping(target = "team", ignore = true)
    Transfer toEntity(TransferRegisterDto transferRegisterDto);
}
