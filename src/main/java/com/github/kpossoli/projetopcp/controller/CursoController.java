package com.github.kpossoli.projetopcp.controller;

import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.CursoDto;
import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.mapper.CursoMapper;
import com.github.kpossoli.projetopcp.mapper.MateriaMapper;
import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.service.CursoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;
    private final CursoMapper cursoMapper;
    private final MateriaMapper materiaMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<CursoDto> obter(@PathVariable Long id) {
        Curso curso = cursoService.obter(id);
        CursoDto cursoDto = cursoMapper.toDto(curso);

        return ResponseEntity.ok(cursoDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<CursoDto>> listar() {
        List<Curso> cursos = cursoService.listar();
        List<CursoDto> cursosDto = cursoMapper.toDto(cursos);

        return ResponseEntity.ok(cursosDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CURSO_WRITE')")
    public ResponseEntity<CursoDto> criar(@RequestBody @Valid CursoDto cursoDto) {
        Curso curso = cursoMapper.toEntity(cursoDto);
        Curso cursoSalvo = cursoService.criar(curso);
        CursoDto cursoSalvoDto = cursoMapper.toDto(cursoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_WRITE')")
    public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid CursoDto cursoDto) {
        Curso curso = cursoMapper.toEntity(cursoDto);
        Curso cursoSalvo = cursoService.atualizar(id, curso);
        CursoDto cursoSalvoDto = cursoMapper.toDto(cursoSalvo);

        return ResponseEntity.ok(cursoSalvoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}

