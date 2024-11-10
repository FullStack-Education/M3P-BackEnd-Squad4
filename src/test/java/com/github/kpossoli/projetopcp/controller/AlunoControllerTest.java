package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.dto.AlunoSimplifiedDto;
import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.AlunoMapper;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.model.Pontuacao;
import com.github.kpossoli.projetopcp.service.AlunoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AlunoControllerTest {

    @Mock
    private AlunoService alunoService;

    @Mock
    private AlunoMapper alunoMapper;

    @Mock
    private NotaMapper notaMapper;

    @InjectMocks
    private AlunoController alunoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void listar() {
        List<Aluno> alunos = Collections.singletonList(new Aluno());
        List<AlunoDto> alunosDto = Collections.singletonList(new AlunoDto());

        when(alunoService.listar()).thenReturn(alunos);
        when(alunoMapper.toDto(alunos)).thenReturn(alunosDto);

        ResponseEntity<List<AlunoDto>> response = alunoController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunosDto, response.getBody());

        verify(alunoService).listar();
        verify(alunoMapper).toDto(alunos);
    }

    @Test
    void obter() {
        Long alunoId = 1L;
        Aluno aluno = new Aluno();
        aluno.setId(alunoId);
        AlunoDto alunoDto = new AlunoDto();
        alunoDto.setId(alunoId);

        when(alunoService.obter(alunoId)).thenReturn(aluno);
        when(alunoMapper.toDto(aluno)).thenReturn(alunoDto);

        ResponseEntity<AlunoDto> response = alunoController.obter(alunoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunoDto, response.getBody());

        verify(alunoService).obter(alunoId);
        verify(alunoMapper).toDto(aluno);
    }

    @Test
    void criar() {
        AlunoDto alunoDto = new AlunoDto();
        Aluno aluno = new Aluno();
        Aluno alunoSalvo = new Aluno();
        AlunoDto alunoSalvoDto = new AlunoDto();

        when(alunoMapper.toEntity(alunoDto)).thenReturn(aluno);
        when(alunoService.criar(aluno)).thenReturn(alunoSalvo);
        when(alunoMapper.toDto(alunoSalvo)).thenReturn(alunoSalvoDto);

        ResponseEntity<AlunoDto> response = alunoController.criar(alunoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunoSalvoDto, response.getBody());

        verify(alunoMapper).toEntity(alunoDto);
        verify(alunoService).criar(aluno);
        verify(alunoMapper).toDto(alunoSalvo);
    }

    @Test
    void atualizar() {
        Long alunoId = 1L;
        AlunoDto alunoDto = new AlunoDto();
        Aluno aluno = new Aluno();
        Aluno alunoSalvo = new Aluno();
        AlunoDto alunoSalvoDto = new AlunoDto();

        when(alunoMapper.toEntity(alunoDto)).thenReturn(aluno);
        when(alunoService.atualizar(alunoId, aluno)).thenReturn(alunoSalvo);
        when(alunoMapper.toDto(alunoSalvo)).thenReturn(alunoSalvoDto);

        ResponseEntity<AlunoDto> response = alunoController.atualizar(alunoId, alunoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunoSalvoDto, response.getBody());

        verify(alunoMapper).toEntity(alunoDto);
        verify(alunoService).atualizar(alunoId, aluno);
        verify(alunoMapper).toDto(alunoSalvo);
    }

    @Test
    void excluir() {
        Long alunoId = 1L;

        ResponseEntity<Void> response = alunoController.excluir(alunoId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(alunoService).excluir(alunoId);
    }

    @Test
    void obterPontuacao() {
        Long alunoId = 1L;
        Pontuacao pontuacao = new Pontuacao();
        pontuacao.setPontuacao(new BigDecimal("10"));

        when(alunoService.obterPontuacao(alunoId)).thenReturn(pontuacao);

        ResponseEntity<Pontuacao> response = alunoController.obterPontuacao(alunoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(new BigDecimal("10"), response.getBody().getPontuacao());

        verify(alunoService).obterPontuacao(alunoId);
    }

    @Test
    void listarNotas() {
        Long alunoId = 1L;
        List<Nota> notas = Collections.singletonList(new Nota());
        List<NotaDto> notasDto = Collections.singletonList(new NotaDto());

        when(alunoService.listarNotas(alunoId)).thenReturn(notas);
        when(notaMapper.toDto(notas)).thenReturn(notasDto);

        ResponseEntity<List<NotaDto>> response = alunoController.listarNotas(alunoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notasDto, response.getBody());

        verify(alunoService).listarNotas(alunoId);
        verify(notaMapper).toDto(notas);
    }

    @Test
    void pegarAlunosPorTurma() {
        Long turmaId = 1L;
        List<AlunoDto> alunosDto = Collections.singletonList(new AlunoDto());

        when(alunoService.pegarAlunosPorTurma(turmaId)).thenReturn(alunosDto);

        ResponseEntity<List<AlunoDto>> response = alunoController.pegarAlunosPorTurma(turmaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunosDto, response.getBody());

        verify(alunoService).pegarAlunosPorTurma(turmaId);
    }

    @Test
    void obterAlunoPorEmail() {
        String email = "aluno@example.com";
        Aluno aluno = new Aluno();
        AlunoSimplifiedDto alunoSimplifiedDto = new AlunoSimplifiedDto();

        when(alunoService.obterAlunoPorEmail(email)).thenReturn(aluno);
        when(alunoMapper.toSimplifiedDto(aluno)).thenReturn(alunoSimplifiedDto);

        ResponseEntity<AlunoSimplifiedDto> response = alunoController.obterAlunoPorEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(alunoSimplifiedDto, response.getBody());

        verify(alunoService).obterAlunoPorEmail(email);
        verify(alunoMapper).toSimplifiedDto(aluno);
    }
}