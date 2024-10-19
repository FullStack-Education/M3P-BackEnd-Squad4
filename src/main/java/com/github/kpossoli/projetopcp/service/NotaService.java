package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Nota;
import java.util.List;

public interface NotaService {

    Nota obter(Long id);

    List<Nota> listar();

    Nota criar(Nota nota);
    
    Nota atualizar(Long id, Nota nota);
    
    void excluir(Long id);

}
