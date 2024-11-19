package com.github.kpossoli.projetopcp.service;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Usuario;

public interface UsuarioService {
    Usuario criarUsuario(Usuario usuario);

    Usuario obterByEmail (String email);

    void excluir (Long id);

}
