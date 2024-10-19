package com.github.kpossoli.projetopcp.controller;

import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.mapper.MateriaMapper;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.service.MateriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/materias")
public class MateriaController {

    private final MateriaService materiaService;
    private final MateriaMapper materiaMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<MateriaDto> obter(@PathVariable Long id) {
        Materia materia = materiaService.obter(id);
        MateriaDto materiaDto = materiaMapper.toDto(materia);

        return ResponseEntity.ok(materiaDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<List<MateriaDto>> listar() {
        List<Materia> materias = materiaService.listar();
        List<MateriaDto> materiasDto = materiaMapper.toDto(materias);

        return ResponseEntity.ok(materiasDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('MATERIA_WRITE')")
    public ResponseEntity<MateriaDto> criar(@RequestBody @Valid MateriaDto materiaDto) {
        Materia materia = materiaMapper.toEntity(materiaDto);
        Materia materiaSalvo = materiaService.criar(materia);
        MateriaDto materiaSalvoDto = materiaMapper.toDto(materiaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(materiaSalvoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MATERIA_WRITE')")
    public ResponseEntity<MateriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid MateriaDto materiaDto) {
        Materia materia = materiaMapper.toEntity(materiaDto);
        Materia materiaSalvo = materiaService.atualizar(id, materia);
        MateriaDto materiaSalvoDto = materiaMapper.toDto(materiaSalvo);

        return ResponseEntity.ok(materiaSalvoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MATERIA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materiaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}

