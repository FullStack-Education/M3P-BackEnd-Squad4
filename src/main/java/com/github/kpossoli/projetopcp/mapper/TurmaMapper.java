package com.github.kpossoli.projetopcp.mapper;

import java.util.List;

import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Docente;
import org.mapstruct.Mapper;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.model.Turma;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    @Mapping(target = "docenteId", source = "docente.id")
    @Mapping(target = "cursoId", source = "curso.id")
    TurmaDto toDto(Turma turma);
    List<TurmaDto> toDto(List<Turma> turmas);

    @Mapping(target = "docente", source = "docenteId")
    @Mapping(target = "curso", source = "cursoId")
    Turma toEntity(TurmaDto turmaDto);

    default Docente map(Long docenteId) {
        if (docenteId == null) {
            return null; // Retorna null se o ID for nulo
        }
        Docente docente = new Docente(); // Crie uma nova instância de Docente
        docente.setId(docenteId); // Defina o ID do docente
        return docente; // Retorna o objeto Docente com o ID configurado
    }
    default Curso mapCurso(Long cursoId) {
        if (cursoId == null) {
            return null;
        }
        Curso curso = new Curso();
        curso.setId(cursoId);
        return curso;
    }


}

