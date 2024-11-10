package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import com.github.kpossoli.projetopcp.dto.AlunoSimplifiedDto;
import com.github.kpossoli.projetopcp.dto.UsuarioSimplifiedDto;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.model.Usuario;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.model.Aluno;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlunoMapper {

    @Mapping(target = "turma", source = "turma.id")
    AlunoDto toDto(Aluno aluno);
    List<AlunoDto> toDto(List<Aluno> aluno);

    @Mapping(target = "turma", source = "turma")
    Aluno toEntity(AlunoDto alunoDto);

    @Mapping(target = "email", source = "email")
    AlunoSimplifiedDto toSimplifiedDto (Aluno aluno);

    default Long mapTurmaToId(Turma turma) {
        return turma != null ? turma.getId() : null;
    }

    default Turma mapTurmaFromId(Long id) {
        if (id == null) {
            return null;
        }
        Turma turma = new Turma();
        turma.setId(id);
        return turma;
    }

}

