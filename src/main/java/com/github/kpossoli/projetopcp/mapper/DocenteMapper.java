package com.github.kpossoli.projetopcp.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.model.Materia;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.DocenteDto;
import com.github.kpossoli.projetopcp.model.Docente;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocenteMapper {

    @Mapping(source = "materias", target = "materias", qualifiedByName = "materiasToIds")
    DocenteDto toDto(Docente docente);
    List<DocenteDto> toDto(List<Docente> docente);
    @Mapping(source = "materias", target = "materias", qualifiedByName = "idsToMaterias")
    Docente toEntity(DocenteDto docenteDto);

    static List<Long> materiasToIds (List<Materia> materias) {
        return materias.stream().map(Materia::getId).collect(Collectors.toList());
    }

    static List<Materia> idsToMaterias(List<Long> ids) {
        return ids.stream().map(id -> {
            Materia materia = new Materia();
            materia.setId(id);
            return materia;
        }).collect(Collectors.toList());
    }

}

