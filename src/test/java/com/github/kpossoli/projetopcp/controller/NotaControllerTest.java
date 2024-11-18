package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.service.NotaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NotaControllerTest {
    @Mock
    private NotaService notaService;

    @Mock
    private NotaMapper notaMapper;

    @InjectMocks
    private NotaController notaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obter() {
        Long notaId = 1L;
        Nota nota = new Nota();
        nota.setId(notaId);
        NotaDto notaDto = new NotaDto();
        notaDto.setId(notaId);

        when(notaService.obter(notaId)).thenReturn(nota);
        when(notaMapper.toDto(nota)).thenReturn(notaDto);

        ResponseEntity<NotaDto> response = notaController.obter(notaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notaDto, response.getBody());

        verify(notaService).obter(notaId);
        verify(notaMapper).toDto(nota);
    }

    @Test
    void listar() {
        List<Nota> notas = Collections.singletonList(new Nota());
        List<NotaDto> notasDto = Collections.singletonList(new NotaDto());

        when(notaService.listar()).thenReturn(notas);
        when(notaMapper.toDto(notas)).thenReturn(notasDto);

        ResponseEntity<List<NotaDto>> response = notaController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notasDto, response.getBody());

        verify(notaService).listar();
        verify(notaMapper).toDto(notas);
    }

    @Test
    void criar() {
        NotaDto notaDto = new NotaDto();
        Nota nota = new Nota();
        Nota notaSalvo = new Nota();
        NotaDto notaSalvoDto = new NotaDto();

        when(notaMapper.toEntity(notaDto)).thenReturn(nota);
        when(notaService.criar(nota)).thenReturn(notaSalvo);
        when(notaMapper.toDto(notaSalvo)).thenReturn(notaSalvoDto);

        ResponseEntity<NotaDto> response = notaController.criar(notaDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notaSalvoDto, response.getBody());

        verify(notaMapper).toEntity(notaDto);
        verify(notaService).criar(nota);
        verify(notaMapper).toDto(notaSalvo);
    }

    @Test
    void atualizar() {
        Long notaId = 1L;
        NotaDto notaDto = new NotaDto();
        Nota nota = new Nota();
        Nota notaSalvo = new Nota();
        NotaDto notaSalvoDto = new NotaDto();

        when(notaMapper.toEntity(notaDto)).thenReturn(nota);
        when(notaService.atualizar(notaId, nota)).thenReturn(notaSalvo);
        when(notaMapper.toDto(notaSalvo)).thenReturn(notaSalvoDto);

        ResponseEntity<NotaDto> response = notaController.atualizar(notaId, notaDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(notaSalvoDto, response.getBody());

        verify(notaMapper).toEntity(notaDto);
        verify(notaService).atualizar(notaId, nota);
        verify(notaMapper).toDto(notaSalvo);
    }

    @Test
    void excluir() {
        Long notaId = 1L;

        ResponseEntity<Void> response = notaController.excluir(notaId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(notaService).excluir(notaId);
    }

   @Test
    void listarNotasPorAluno() {
       Long idAluno = 1L;

       Nota nota1 = new Nota();
       nota1.setId(1L);
       Aluno aluno1 = new Aluno();
       aluno1.setId(idAluno);
       nota1.setAluno(aluno1);

       Nota nota2 = new Nota();
       nota2.setId(2L);
       Aluno aluno2 = new Aluno();
       aluno2.setId(2L);
       nota2.setAluno(aluno2);

       List<Nota> notasMock = List.of(nota1, nota2);
       when(notaService.listar()).thenReturn(notasMock);

       NotaDto notaDto1 = new NotaDto();
       notaDto1.setId(1L);
       when(notaMapper.toDto(anyList())).thenReturn(List.of(notaDto1));

       ResponseEntity<List<NotaDto>> response = notaController.listarNotasPorAluno(idAluno);

       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertNotNull(response.getBody());
       assertEquals(1, response.getBody().size());
       assertEquals(notaDto1.getId(), response.getBody().get(0).getId());

       verify(notaService).listar();
       verify(notaMapper).toDto(anyList());
    }
}