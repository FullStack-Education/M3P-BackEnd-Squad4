package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.model.Pontuacao;

import java.util.List;

public interface AlunoService {

    Aluno obter(Long id);

    List<Aluno> listar();

    Aluno criar(Aluno aluno);
    
    Aluno atualizar(Long id, Aluno aluno);
    
    void excluir(Long id);

    Pontuacao obterPontuacao(Long id);

    List<Nota> notas(Long id);

}
