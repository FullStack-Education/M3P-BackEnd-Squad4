package com.github.kpossoli.projetopcp.service;

import java.util.List;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.mapper.TurmaMapper;
import com.github.kpossoli.projetopcp.model.Docente;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.repository.DocenteRepository;
import com.github.kpossoli.projetopcp.repository.TurmaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TurmaServiceImpl implements TurmaService {

    private final TurmaRepository turmaRepository;
    private final DocenteRepository docenteRepository;
    private final TurmaMapper turmaMapper;

    @Override
    public Turma obter(Long id) {
        return turmaRepository.findById(id)
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    public List<TurmaDto> obterTurmasPorDocente(Long docenteId) {
        return turmaMapper.toDto(turmaRepository.findByDocenteId(docenteId));
    }

    @Override
    public List<Turma> listar() {
        return turmaRepository.findAll();
    }

    @Override
    public Turma criar(Turma turma) {
        log.info("Criando turma", turma);

        var docente = docenteRepository.findById(turma.getDocente().getId())
            .orElseThrow(() -> new EmptyResultDataAccessException(1));
            
            
        turma.setDocente(docente);

        return turmaRepository.save(turma);
    }
    
    @Override
    public Turma atualizar(Long id, TurmaDto turmaDto) {
        log.info("Atualizando turma de id: {}", id);

        Turma turmaSalva = obter(id);

		BeanUtils.copyProperties(turmaDto, turmaSalva,     "id", "docente");

        if(turmaDto.getDocenteId() == null) {
            turmaSalva.setDocente(null);
        } else {
            Docente docente = docenteRepository.findById(turmaDto.getDocenteId())
                    .orElseThrow(() -> new RuntimeException("Docente não encontrado"));
            turmaSalva.setDocente(docente);
        }

        return turmaRepository.save(turmaSalva);
    }
    
    @Override
    public void excluir(Long id) {
        log.info("Excluindo turma de id: {}", id);

        turmaRepository.deleteById(id);
    }
        
}
