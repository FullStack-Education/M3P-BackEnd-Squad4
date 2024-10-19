package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.model.Aluno;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    AlunoDto toDto(Aluno aluno);
    List<AlunoDto> toDto(List<Aluno> aluno);

    Aluno toEntity(AlunoDto alunoDto);

}

