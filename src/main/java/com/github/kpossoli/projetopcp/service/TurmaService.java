package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Turma;
import java.util.List;

public interface TurmaService {

    Turma obter(Long id);

    List<Turma> listar();

    Turma criar(Turma turma);
    
    Turma atualizar(Long id, Turma turma);
    
    void excluir(Long id);

}
