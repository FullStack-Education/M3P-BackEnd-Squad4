package com.github.kpossoli.projetopcp.service;

import java.util.List;

import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.repository.PapelRepository;
import com.github.kpossoli.projetopcp.repository.TurmaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.repository.DocenteRepository;
import com.github.kpossoli.projetopcp.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class DocenteServiceImpl implements DocenteService {

    private final DocenteRepository docenteRepository;
    private final UsuarioService usuarioService;
    private final PapelRepository papelRepository;
    private final TurmaRepository turmaRepository;

    @Override
    public Docente obter(Long id) {
        return docenteRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<Docente> listar() {
        return docenteRepository.findAll();
    }

    @Override
    public Docente criar(Docente docente) {
        log.info("Criando docente", docente);

        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(docente.getNomeCompleto());
        novoUsuario.setEmail(docente.getEmail());
        novoUsuario.setSenha(docente.getSenha());
        novoUsuario.setPapel(papelRepository.findByNome("PROFESSOR").orElseThrow(() -> new RuntimeException("Papel não encontrado")));

        Usuario usuarioSalvo = usuarioService.criarUsuario(novoUsuario);

        docente.setUsuario(usuarioSalvo);

        return docenteRepository.save(docente);
    }
    
    @Override
    public Docente atualizar(Long id, Docente docente) {
        log.info("Atualizando docente de id: {}", id);

        Docente docenteSalvo = obter(id);
		BeanUtils.copyProperties(docente, docenteSalvo, "id");
        return docenteRepository.save(docenteSalvo);
    }
    
    @Override
    public void excluir(Long id) {
        log.info("Excluindo docente de id: {}", id);

        Docente docente = obter(id);

        List<Turma> turmasAssociadas = turmaRepository.findByDocenteId(docente.getId());
        for (Turma turma : turmasAssociadas) {
            turma.setDocente(null);
            turmaRepository.save(turma);
        }

        if(docente.getUsuario() != null) {
            usuarioService.excluir(docente.getUsuario().getId());
        }

        docenteRepository.deleteById(id);
    }
        
}
