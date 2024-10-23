package com.github.kpossoli.projetopcp.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) {
		Usuario usuario = usuarioRepository.findByLogin(login)
			.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));

		return new User(login, usuario.getSenha(), getPermissoes(usuario));
	}

private Collection<? extends GrantedAuthority> getPermissoes(Usuario usuario) {
    Set<String> permissoes = new HashSet<>();


    if (usuario.getPapel().getNome().equals("ADM")) {        
        permissoes.add("USUARIO_READ");
        permissoes.add("USUARIO_WRITE");
        permissoes.add("USUARIO_DELETE");

        permissoes.add("TURMA_READ");
        permissoes.add("TURMA_WRITE");
        permissoes.add("TURMA_DELETE");

        permissoes.add("CURSO_READ");
        permissoes.add("CURSO_WRITE");
        permissoes.add("CURSO_DELETE");

        permissoes.add("DOCENTE_READ");
        permissoes.add("DOCENTE_WRITE");
        permissoes.add("DOCENTE_DELETE");

        permissoes.add("ALUNO_READ");
        permissoes.add("ALUNO_WRITE");
        permissoes.add("ALUNO_DELETE");

        permissoes.add("NOTA_READ");
        permissoes.add("NOTA_WRITE");
        permissoes.add("NOTA_DELETE");

        permissoes.add("MATERIA_READ");
        permissoes.add("MATERIA_WRITE");
        permissoes.add("MATERIA_DELETE");

        permissoes.add("PONTUACAO_READ");

        permissoes.add("ESTATISTICAS_READ");
    } else if (usuario.getPapel().getNome().equals("PEDAGOGICO")) {
        permissoes.add("TURMA_READ");
        permissoes.add("TURMA_WRITE");

        permissoes.add("CURSO_READ");
        permissoes.add("CURSO_WRITE");

        permissoes.add("DOCENTE_READ");
        permissoes.add("DOCENTE_WRITE");

        permissoes.add("ALUNO_READ");
        permissoes.add("ALUNO_WRITE");

        permissoes.add("PONTUACAO_READ");

        permissoes.add("ESTATISTICAS_READ");
    } else if (usuario.getPapel().getNome().equals("RECRUITER")) {
        permissoes.add("DOCENTE_READ");
        permissoes.add("DOCENTE_WRITE");
    } else if (usuario.getPapel().getNome().equals("PROFESSOR")) {

        permissoes.add("NOTA_READ");
        permissoes.add("NOTA_WRITE");

        permissoes.add("PONTUACAO_READ");
        permissoes.add("ESTATISTICAS_READ");
    } else {
        permissoes.add("NOTA_READ");
        permissoes.add("PONTUACAO_READ");
    }

    return permissoes.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
    }

}
