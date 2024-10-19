package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.model.Nota;

@Mapper(componentModel = "spring")
public interface NotaMapper {

    NotaDto toDto(Nota nota);
    List<NotaDto> toDto(List<Nota> nota);

    Nota toEntity(NotaDto notaDto);

}

