package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.model.Curso;
import java.util.List;

public interface CursoService {

    Curso obter(Long id);

    List<Curso> listar();

    Curso criar(Curso curso);
    
    Curso atualizar(Long id, Curso curso);
    
    void excluir(Long id);

    List<Materia> listarMaterias(Long id);

}
