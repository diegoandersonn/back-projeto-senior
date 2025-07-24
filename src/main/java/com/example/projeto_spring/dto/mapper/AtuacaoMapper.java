package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Atuacao;
import com.example.projeto_spring.dto.atuacao.DtoCadastroAtuacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AtuacaoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "partida", ignore = true)
    @Mapping(target = "jogador", ignore = true)
    Atuacao toEntity(DtoCadastroAtuacao dtoCadastroAtuacao);
}
