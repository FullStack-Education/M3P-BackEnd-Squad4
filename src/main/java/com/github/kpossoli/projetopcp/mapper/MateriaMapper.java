package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.model.Materia;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    MateriaDto toDto(Materia materia);
    List<MateriaDto> toDto(List<Materia> materia);

    Materia toEntity(MateriaDto materiaDto);

}

