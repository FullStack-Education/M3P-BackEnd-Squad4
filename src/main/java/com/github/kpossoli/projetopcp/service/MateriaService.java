package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Materia;
import java.util.List;

public interface MateriaService {

    Materia obter(Long id);

    List<Materia> listar();

    Materia criar(Materia materia);
    
    Materia atualizar(Long id, Materia materia);
    
    void excluir(Long id);

}
