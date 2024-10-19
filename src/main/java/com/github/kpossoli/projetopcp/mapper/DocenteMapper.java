package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.DocenteDto;
import com.github.kpossoli.projetopcp.model.Docente;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    DocenteDto toDto(Docente docente);
    List<DocenteDto> toDto(List<Docente> docente);

    Docente toEntity(DocenteDto docenteDto);

}

