package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.TurmaService;
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
    private final AlunoService alunoService;
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

    @GetMapping("/{id}/materias")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<MateriaDto>> listarMaterias(@PathVariable Long id) {
        List<Materia> materias = cursoService.listarMaterias(id);
        List<MateriaDto> materiasDto = materiaMapper.toDto(materias);

        return ResponseEntity.ok(materiasDto);
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

    @GetMapping("/aluno/{idAluno}")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<CursoDto>> obterCursosDoAluno(@PathVariable Long idAluno) {
        Aluno aluno = alunoService.obter(idAluno);
        List<Curso> cursosDoAluno = new java.util.ArrayList<>(List.of());
        List<Curso> cursos = cursoService.listar();
        for (Curso c : cursos){
            for (Turma t: c.getTurmas()){
                for (Aluno a: t.getAlunos()){
                    if (a.equals(aluno)){
                        cursosDoAluno.add(c);
                    }
                }
            }
        }
        List<CursoDto> cursosDto = cursoMapper.toDto(cursosDoAluno);
        return ResponseEntity.ok(cursosDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}