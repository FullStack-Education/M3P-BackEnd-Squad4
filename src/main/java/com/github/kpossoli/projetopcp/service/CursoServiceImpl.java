package com.github.kpossoli.projetopcp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.mapper.MateriaMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.repository.CursoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;
    private final MateriaMapper materiaMapper;

    @Override
    public Curso obter(Long id) {
        return cursoRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<MateriaDto> obterMateriasPorCursoEDocente(Long idCurso, Long idDocente) throws ChangeSetPersister.NotFoundException {
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        List<Materia> materiasRelacionadas = curso.getTurmas().stream()
                .filter(turma -> turma.getDocente() != null && turma.getDocente().getId().equals(idDocente))
                .flatMap(turma -> turma.getDocente().getMaterias().stream())
                .filter(materia -> materia.getCursos().contains(curso))
                .toList();

        return materiaMapper.toDto(new ArrayList<>(materiasRelacionadas));
    }

    @Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Curso criar(Curso curso) {
        log.info("Criando curso", curso);
        
        return cursoRepository.save(curso);
    }
    
    @Override
    public Curso atualizar(Long id, Curso curso) {
        log.info("Atualizando curso de id: {}", id);

        Curso cursoSalvo = obter(id);
		BeanUtils.copyProperties(curso, cursoSalvo, "id");
        return cursoRepository.save(cursoSalvo);
    }
    
    @Override
    public void excluir(Long id) {
        log.info("Excluindo curso de id: {}", id);

        cursoRepository.deleteById(id);
    }

    @Override
    public List<Materia> listarMaterias(Long id) {
        Curso curso = obter(id);
        return curso.getMaterias();
    }
        
}
