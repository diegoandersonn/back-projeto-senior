package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Contrato;
import com.example.projeto_spring.domain.Jogador;
import com.example.projeto_spring.dto.contrato.DtoCadastroContrato;
import com.example.projeto_spring.dto.jogador.DtoCadastroJogador;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface JogadorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "time", ignore = true)
    @Mapping(target = "nacionalidade", ignore = true)
    @Mapping(target = "contrato", expression = "java(mapContrato(dtoCadastroJogador.contrato()))")
    Jogador toEntity(DtoCadastroJogador dtoCadastroJogador);

    default Contrato mapContrato(DtoCadastroContrato dto) {
        if (dto == null) return null;
        return new Contrato(dto);
    }
}

