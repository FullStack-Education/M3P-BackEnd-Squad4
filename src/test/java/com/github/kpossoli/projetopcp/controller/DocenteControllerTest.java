package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.DocenteDto;
import com.github.kpossoli.projetopcp.mapper.DocenteMapper;
import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.repository.DocenteRepository;
import com.github.kpossoli.projetopcp.service.DocenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DocenteControllerTest {
    @Mock
    private DocenteService docenteService;

    @Mock
    private DocenteMapper docenteMapper;

    @InjectMocks
    private DocenteController docenteController;

    @Mock
    private DocenteRepository docenteRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void obter() {
        Docente docente = new Docente();
        docente.setId(1L);
        docente.setNomeCompleto("Prof. Pedro");

        DocenteDto docenteDto = new DocenteDto();
        docenteDto.setId(1L);
        docenteDto.setNomeCompleto("Prof. Pedro");

        when(docenteService.obter(1L)).thenReturn(docente);
        when(docenteMapper.toDto(docente)).thenReturn(docenteDto);

        ResponseEntity<DocenteDto> response = docenteController.obter(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Prof. Pedro", response.getBody().getNomeCompleto());
    }

    @Test
    void listar() {
        Docente docente1 = new Docente();
        Docente docente2 = new Docente();
        List<Docente> docentes = Arrays.asList(docente1, docente2);

        DocenteDto docenteDto1 = new DocenteDto();
        DocenteDto docenteDto2 = new DocenteDto();
        List<DocenteDto> docentesDto = Arrays.asList(docenteDto1, docenteDto2);

        when(docenteService.listar()).thenReturn(docentes);
        when(docenteMapper.toDto(docentes)).thenReturn(docentesDto);

        ResponseEntity<List<DocenteDto>> response = docenteController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void criar() {
        DocenteDto docenteDto = new DocenteDto();
        docenteDto.setNomeCompleto("Prof. Matheus");

        Docente docente = new Docente();
        docente.setNomeCompleto("Prof. Matheus");

        Docente docenteSalvo = new Docente();
        docenteSalvo.setId(1L);
        docenteSalvo.setNomeCompleto("Prof. Matheus");

        DocenteDto docenteSalvoDto = new DocenteDto();
        docenteSalvoDto.setId(1L);
        docenteSalvoDto.setNomeCompleto("Prof. Matheus");

        when(docenteMapper.toEntity(docenteDto)).thenReturn(docente);
        when(docenteService.criar(docente)).thenReturn(docenteSalvo);
        when(docenteMapper.toDto(docenteSalvo)).thenReturn(docenteSalvoDto);

        ResponseEntity<DocenteDto> response = docenteController.criar(docenteDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Prof. Matheus", response.getBody().getNomeCompleto());
    }

    @Test
    void atualizar() {
        DocenteDto docenteAtualizadoDto = new DocenteDto();
        docenteAtualizadoDto.setNomeCompleto("Prof. Caio");

        Docente docenteExistente = new Docente();
        docenteExistente.setId(1L);
        docenteExistente.setNomeCompleto("Prof. Caio");

        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docenteExistente));
        when(docenteRepository.save(docenteExistente)).thenReturn(docenteExistente);

        Docente result = docenteController.atualizar(1L, docenteAtualizadoDto);

        assertNotNull(result);
        assertEquals("Prof. Caio", result.getNomeCompleto());
    }

    @Test
    void excluir() {
        ResponseEntity<Void> response = docenteController.excluir(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(docenteService, times(1)).excluir(1L);
    }
}