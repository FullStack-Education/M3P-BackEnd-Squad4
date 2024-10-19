package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.CursoDto;
import com.github.kpossoli.projetopcp.model.Curso;

@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoDto toDto(Curso curso);
    List<CursoDto> toDto(List<Curso> cursos);

    Curso toEntity(CursoDto cursoDto);

}

