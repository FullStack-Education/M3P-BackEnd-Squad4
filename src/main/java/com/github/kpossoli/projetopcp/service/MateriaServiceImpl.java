package com.github.kpossoli.projetopcp.service;

import java.util.List;

import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.repository.MateriaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final CursoService cursoService;
    private final CursoRepository cursoRepository;

    @Override
    public Materia obter(Long id) {
        return materiaRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<Materia> listar() {
        return materiaRepository.findAll();
    }

    @Override
    public List<Materia> listarMateriasPorCurso(Long idCurso) {

        Curso curso = cursoService.obter(idCurso);
        return curso.getMaterias();
    }

    @Override
    public Materia criar(Materia materia) {
        log.info("Criando materia", materia);

        return materiaRepository.save(materia);
    }
    
    @Override
    public Materia atualizar(Long id, Materia materia) {
        log.info("Atualizando materia de id: {}", id);

        Materia materiaSalvo = obter(id);
		BeanUtils.copyProperties(materia, materiaSalvo, "id");
        return materiaRepository.save(materiaSalvo);
    }
    
    @Override
    public void excluir(Long id) {
        log.info("Excluindo materia de id: {}", id);

        materiaRepository.deleteById(id);
    }
        
}
