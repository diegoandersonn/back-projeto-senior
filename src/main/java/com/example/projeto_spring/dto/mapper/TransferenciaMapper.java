package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Transferencia;
import com.example.projeto_spring.dto.transferencia.DtoCadastroTransferencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TransferenciaMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "jogador", ignore = true)
    @Mapping(target = "time", ignore = true)
    Transferencia toEntity(DtoCadastroTransferencia dtoCadastroTransferencia);
}
