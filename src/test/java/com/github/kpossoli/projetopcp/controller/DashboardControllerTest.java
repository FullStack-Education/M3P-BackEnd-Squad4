package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import com.github.kpossoli.projetopcp.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DashboardControllerTest {

    @Mock
    private AlunoService alunoService;

    @Mock
    private DocenteService docenteService;

    @Mock
    private TurmaService turmaService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEstatisticas() {
        when(alunoService.listar()).thenReturn(List.of(new Aluno()));
        when(docenteService.listar()).thenReturn(List.of(new Docente(), new Docente()));
        when(turmaService.listar()).thenReturn(List.of(new Turma(), new Turma(), new Turma()));

        ResponseEntity<Map<String, Integer>> response = dashboardController.getEstatisticas();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        Map<String, Integer> estatisticas = response.getBody();
        assertNotNull(estatisticas);
        assertEquals(1, estatisticas.get("quantidadeDeAlunos"));
        assertEquals(2, estatisticas.get("quantidadeDeDocentes"));
        assertEquals(3, estatisticas.get("quantidadeDeTurmas"));

        verify(alunoService).listar();
        verify(docenteService).listar();
        verify(turmaService).listar();
    }
}