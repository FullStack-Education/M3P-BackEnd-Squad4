package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Docente;
import java.util.List;

public interface DocenteService {

    Docente obter(Long id);

    List<Docente> listar();

    Docente criar(Docente docente);
    
    Docente atualizar(Long id, Docente docente);
    
    void excluir(Long id);

}
