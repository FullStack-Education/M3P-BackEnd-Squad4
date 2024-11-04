package com.github.kpossoli.projetopcp.mapper;

import java.util.ArrayList;
import java.util.List;

import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Docente;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.model.Materia;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MateriaMapper {

    MateriaDto toDto(Materia materia);
    List<MateriaDto> toDto(List<Materia> materia);

    @Mapping(target = "cursos", expression = "java(mapCursoIdToList(materiaDto.getCursoId()))")
    @Mapping(target = "docentes", expression = "java(mapDocenteIdToList(materiaDto.getDocenteId()))")
    Materia toEntity(MateriaDto materiaDto);

    default List<Docente> mapDocenteIdToList(Long docenteId) {
        List<Docente> docentes = new ArrayList<>();
        if(docenteId != null) {
            Docente docente = new Docente();
            docente.setId(docenteId);
            docentes.add(docente);
        }
        return docentes;
    }

    default List<Curso> mapCursoIdToList(Long cursoId) {
        List<Curso> cursos = new ArrayList<>();
        if(cursoId != null) {
            Curso curso = new Curso();
            curso.setId(cursoId);
            cursos.add(curso);
        }
        return cursos;
    }

}

