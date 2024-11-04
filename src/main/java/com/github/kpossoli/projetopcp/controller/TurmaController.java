package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.CursoDto;
import com.github.kpossoli.projetopcp.mapper.CursoMapperImpl;
import com.github.kpossoli.projetopcp.model.Curso;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.mapper.TurmaMapper;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.service.TurmaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;
    private final TurmaMapper turmaMapper;
    private final CursoMapperImpl cursoMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<TurmaDto> obter(@PathVariable Long id) {
        Turma turma = turmaService.obter(id);
        TurmaDto turmaDto = turmaMapper.toDto(turma);

        return ResponseEntity.ok(turmaDto);
    }

    @GetMapping("/{id}/curso")
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<CursoDto> obterCurso(@PathVariable Long id) {
        Turma turma = turmaService.obter(id);

        if (turma == null || turma.getCurso() == null) {
            return ResponseEntity.notFound().build();
        }

        CursoDto cursoDto = cursoMapper.toDto(turma.getCurso());
        return ResponseEntity.ok(cursoDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<List<TurmaDto>> listar() {
        List<Turma> turmas = turmaService.listar();
        List<TurmaDto> turmasDto = turmaMapper.toDto(turmas);

        return ResponseEntity.ok(turmasDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('TURMA_WRITE')")
    public ResponseEntity<TurmaDto> criar(@RequestBody @Valid TurmaDto turmaDto) {
        Turma turma = turmaMapper.toEntity(turmaDto);
        Turma turmaSalvo = turmaService.criar(turma);
        TurmaDto turmaSalvoDto = turmaMapper.toDto(turmaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalvoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_WRITE')")
    public ResponseEntity<TurmaDto> atualizar(@PathVariable Long id, @RequestBody @Valid TurmaDto turmaDto) {
        Turma turma = turmaMapper.toEntity(turmaDto);
        Turma turmaSalvo = turmaService.atualizar(id, turmaDto);
        TurmaDto turmaSalvoDto = turmaMapper.toDto(turmaSalvo);

        return ResponseEntity.ok(turmaSalvoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        turmaService.excluir(id);
        
        return ResponseEntity.noContent().build();
    }
}

