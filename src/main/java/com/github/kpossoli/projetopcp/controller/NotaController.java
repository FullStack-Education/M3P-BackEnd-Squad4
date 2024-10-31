package com.github.kpossoli.projetopcp.controller;

import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.service.NotaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;
    private final NotaMapper notaMapper;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<NotaDto> obter(@PathVariable Long id) {
        Nota nota = notaService.obter(id);
        NotaDto notaDto = notaMapper.toDto(nota);

        return ResponseEntity.ok(notaDto);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listar() {
        List<Nota> notas = notaService.listar();
        List<NotaDto> notasDto = notaMapper.toDto(notas);

        return ResponseEntity.ok(notasDto);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('NOTA_WRITE')")
    public ResponseEntity<NotaDto> criar(@RequestBody @Valid NotaDto notaDto) {
        Nota nota = notaMapper.toEntity(notaDto);
        Nota notaSalvo = notaService.criar(nota);
        NotaDto notaSalvoDto = notaMapper.toDto(notaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalvoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_WRITE')")
    public ResponseEntity<NotaDto> atualizar(@PathVariable Long id, @RequestBody @Valid NotaDto notaDto) {
        Nota nota = notaMapper.toEntity(notaDto);
        Nota notaSalvo = notaService.atualizar(id, nota);
        NotaDto notaSalvoDto = notaMapper.toDto(notaSalvo);

        return ResponseEntity.ok(notaSalvoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/alunos/{id_aluno}/notas")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listarNotasPorAluno(@PathVariable Long id) {
        List<Nota> notas = notaService.listar();
        List<Nota> notasDoAluno = new java.util.ArrayList<>(List.of());
        for (Nota nota : notas){
            if (nota.getAluno().getId().equals(id)){
                notasDoAluno.add(nota);
            }
        }
        List<NotaDto> notasDoAlunoDTO = notaMapper.toDto(notasDoAluno);
        return  ResponseEntity.ok(notasDoAlunoDTO);
    }
}

