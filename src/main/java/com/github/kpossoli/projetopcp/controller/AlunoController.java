package com.github.kpossoli.projetopcp.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.AlunoMapper;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.model.Pontuacao;
import com.github.kpossoli.projetopcp.service.AlunoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoMapper alunoMapper;
    private final NotaMapper notaMapper;

    @GetMapping
    @PreAuthorize("hasAuthority('ALUNO_READ')")
    public ResponseEntity<List<AlunoDto>> listar() {
        List<Aluno> alunos = alunoService.listar();
        List<AlunoDto> alunosDto = alunoMapper.toDto(alunos);

        return ResponseEntity.ok(alunosDto);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_READ')")
    public ResponseEntity<AlunoDto> obter(@PathVariable Long id) {
        Aluno aluno = alunoService.obter(id);
        AlunoDto alunoDto = alunoMapper.toDto(aluno);

        return ResponseEntity.ok(alunoDto);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasAuthority('ALUNO_WRITE')")
    public ResponseEntity<AlunoDto> criar(@RequestBody @Valid AlunoDto alunoDto) {
        Aluno aluno = alunoMapper.toEntity(alunoDto);
        Aluno alunoSalvo = alunoService.criar(aluno);
        AlunoDto alunoSalvoDto = alunoMapper.toDto(alunoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvoDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_WRITE')")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoDto) {
        Aluno aluno = alunoMapper.toEntity(alunoDto);
        Aluno alunoSalvo = alunoService.atualizar(id, aluno);
        AlunoDto alunoSalvoDto = alunoMapper.toDto(alunoSalvo);

        return ResponseEntity.ok(alunoSalvoDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alunoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pontuacao")
    @PreAuthorize("hasAuthority('PONTUACAO_READ')")
    public ResponseEntity<Pontuacao> obterPontuacao(@PathVariable Long id) {
        Pontuacao pontuacao = alunoService.obterPontuacao(id);

        return ResponseEntity.ok(pontuacao);
    }

    @GetMapping("/{id}/notas")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listar(@PathVariable Long id) {
        List<Nota> notas = alunoService.notas(id);
        List<NotaDto> alunosDto = notaMapper.toDto(notas);

        return ResponseEntity.ok(alunosDto);
    }


}


