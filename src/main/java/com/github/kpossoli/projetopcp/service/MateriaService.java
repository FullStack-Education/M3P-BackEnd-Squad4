package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.model.Materia;
import java.util.List;

public interface MateriaService {

    Materia obter(Long id);

    List<Materia> listar();

    List<Materia> listarMateriasPorCurso(Long id);

    Materia criar(MateriaDto materiaDto);
    
    Materia atualizar(Long id, MateriaDto materiaDto);
    
    void excluir(Long id);

}
