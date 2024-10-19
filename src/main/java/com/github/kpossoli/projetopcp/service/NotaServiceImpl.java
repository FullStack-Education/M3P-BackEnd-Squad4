package com.github.kpossoli.projetopcp.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.repository.AlunoRepository;
import com.github.kpossoli.projetopcp.repository.DocenteRepository;
import com.github.kpossoli.projetopcp.repository.MateriaRepository;
import com.github.kpossoli.projetopcp.repository.NotaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotaServiceImpl implements NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final MateriaRepository materiaRepository;
    private final DocenteRepository docenteRepository;

    @Override
    public Nota obter(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
    }

    @Override
    public List<Nota> listar() {
        return notaRepository.findAll();
    }

    @Override
    public Nota criar(Nota nota) {
        log.info("Criando nota", nota);

        nota.setAluno(alunoRepository.findById(nota.getAluno().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
        nota.setMateria(materiaRepository.findById(nota.getMateria().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
        nota.setDocente(docenteRepository.findById(nota.getDocente().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));

        return notaRepository.save(nota);
    }

    @Override
    public Nota atualizar(Long id, Nota nota) {
        log.info("Atualizando nota de id: {}", id);

        Nota notaSalvo = obter(id);
        BeanUtils.copyProperties(nota, notaSalvo, "id");

        notaSalvo.setAluno(alunoRepository.findById(nota.getAluno().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
        notaSalvo.setMateria(materiaRepository.findById(nota.getMateria().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));
        notaSalvo.setDocente(docenteRepository.findById(nota.getDocente().getId())
                .orElseThrow(() -> new EmptyResultDataAccessException(1)));

        return notaRepository.save(notaSalvo);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo nota de id: {}", id);

        notaRepository.deleteById(id);
    }

}
