package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Turma;
import java.util.List;

public interface TurmaService {

    Turma obter(Long id);

    Curso obterCurso(Long id);

    List<Turma> listar();

    Turma criar(Turma turma);
    
    Turma atualizar(Long id, TurmaDto turmaDto);
    
    void excluir(Long id);

}
