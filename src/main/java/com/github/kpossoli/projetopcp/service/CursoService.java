package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.model.Curso;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CursoService {

    Curso obter(Long id);

    List<Curso> listar();

    Curso criar(Curso curso);
    
    Curso atualizar(Long id, Curso curso);
    
    void excluir(Long id);

    List<Materia> listarMaterias(Long id);

    List<MateriaDto> obterMateriasPorCursoEDocente(Long idCurso, Long idDocente) throws ChangeSetPersister.NotFoundException;

}
