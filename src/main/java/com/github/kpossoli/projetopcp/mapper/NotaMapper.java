package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.model.Materia;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.model.Nota;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotaMapper {

    @Mapping(source = "aluno.id", target = "aluno")
    @Mapping(source = "docente.id", target = "docente")
    @Mapping(source = "materia.id", target = "materia")
    NotaDto toDto(Nota nota);
    List<NotaDto> toDto(List<Nota> nota);
    @Mapping(source = "aluno", target = "aluno")
    @Mapping(source = "docente", target = "docente")
    @Mapping(source = "materia", target = "materia")
    Nota toEntity(NotaDto notaDto);

    default Aluno mapIdToAluno(Long id) {
        if (id == null) {
            return null;
        }
        Aluno aluno = new Aluno();
        aluno.setId(id);
        return aluno;
    }

    default Docente mapIdToDocente(Long id) {
        if (id == null) {
            return null;
        }
        Docente docente = new Docente();
        docente.setId(id);
        return docente;
    }

    default Materia mapIdToMateria(Long id) {
        if (id == null) {
            return null;
        }
        Materia materia = new Materia();
        materia.setId(id);
        return materia;
    }

}

