package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.model.Turma;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    TurmaDto toDto(Turma turma);
    List<TurmaDto> toDto(List<Turma> turmas);

    Turma toEntity(TurmaDto turmaDto);

}

