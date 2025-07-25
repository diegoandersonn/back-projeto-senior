package com.example.projeto_spring.dto.mapper;

import com.example.projeto_spring.domain.Performance;
import com.example.projeto_spring.dto.performance.RegisterPerformanceDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PerformanceMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "match", ignore = true)
    @Mapping(target = "player", ignore = true)
    Performance toEntity(RegisterPerformanceDto registerPerformanceDto);
}
