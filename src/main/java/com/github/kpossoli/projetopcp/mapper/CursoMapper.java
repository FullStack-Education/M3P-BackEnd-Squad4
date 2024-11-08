package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import com.github.kpossoli.projetopcp.dto.CursoSimplifiedDto;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.CursoDto;
import com.github.kpossoli.projetopcp.model.Curso;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface CursoMapper {

    CursoDto toDto(Curso curso);
    List<CursoDto> toDto(List<Curso> cursos);
    Curso toEntity(CursoDto cursoDto);
    CursoSimplifiedDto toSimplifiedDto(Curso curso);
    List<CursoSimplifiedDto> toSimplifiedDto(List<Curso> cursos);

}

