package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Materia;
import java.util.List;

public interface MateriaService {

    Materia obter(Long id);

    List<Materia> listar();

    List<Materia> listarMateriasPorCurso(Long id);

    Materia criar(Materia materia);
    
    Materia atualizar(Long id, Materia materia);
    
    void excluir(Long id);

}
