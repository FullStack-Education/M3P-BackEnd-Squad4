package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.DocenteSimplifiedDto;
import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.mapper.DocenteMapper;
import com.github.kpossoli.projetopcp.mapper.TurmaMapper;
import com.github.kpossoli.projetopcp.model.*;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.repository.TurmaRepository;
import com.github.kpossoli.projetopcp.service.CursoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import com.github.kpossoli.projetopcp.service.TurmaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TurmaControllerTest {

    @Mock
    private TurmaService turmaService;

    @Mock
    private TurmaMapper turmaMapper;

    @Mock
    private TurmaRepository turmaRepository;

    @InjectMocks
    private TurmaController turmaController;

    @Mock
    private CursoService cursoService;

    @Mock
    private DocenteService docenteService;


    @Mock
    private DocenteMapper docenteMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void obter() {
        Turma turma = new Turma();
        turma.setId(1L);
        turma.setNomeTurma("Turma A");

        TurmaDto turmaDto = new TurmaDto();
        turmaDto.setId(1L);
        turmaDto.setNomeTurma("Turma A");

        when(turmaService.obter(1L)).thenReturn(turma);
        when(turmaMapper.toDto(turma)).thenReturn(turmaDto);

        ResponseEntity<TurmaDto> response = turmaController.obter(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Turma A", response.getBody().getNomeTurma());
    }

    @Test
    void listar() {
        Turma turma1 = new Turma();
        Turma turma2 = new Turma();
        List<Turma> turmas = Arrays.asList(turma1, turma2);

        TurmaDto turmaDto1 = new TurmaDto();
        TurmaDto turmaDto2 = new TurmaDto();
        List<TurmaDto> turmasDto = Arrays.asList(turmaDto1, turmaDto2);

        when(turmaService.listar()).thenReturn(turmas);
        when(turmaMapper.toDto(turmas)).thenReturn(turmasDto);

        ResponseEntity<List<TurmaDto>> response = turmaController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void criar() {
        TurmaDto turmaDto = new TurmaDto();
        turmaDto.setNomeTurma("Turma A");
        Turma turma = new Turma();
        turma.setNomeTurma("Turma A");

        Turma turmaSalvo = new Turma();
        turmaSalvo.setId(1L);
        turmaSalvo.setNomeTurma("Turma A");

        TurmaDto turmaSalvoDto = new TurmaDto();
        turmaSalvoDto.setId(1L);
        turmaSalvoDto.setNomeTurma("Turma A");

        when(turmaMapper.toEntity(turmaDto)).thenReturn(turma);
        when(turmaService.criar(turma)).thenReturn(turmaSalvo);
        when(turmaMapper.toDto(turmaSalvo)).thenReturn(turmaSalvoDto);

        ResponseEntity<TurmaDto> response = turmaController.criar(turmaDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Turma A", response.getBody().getNomeTurma());
    }

    @Test
    void atualizar() {
        Long id = 1L;

        TurmaDto turmaDto = new TurmaDto();
        turmaDto.setId(id);
        turmaDto.setNomeTurma("Truma");

        Turma turmaAtualizada = new Turma();
        turmaAtualizada.setId(id);
        turmaAtualizada.setNomeTurma("Truma");

        TurmaDto turmaAtualizadaDto = new TurmaDto();
        turmaAtualizadaDto.setId(id);
        turmaAtualizadaDto.setNomeTurma("Truma");

        Turma turmaSalva = new Turma();
        turmaSalva.setId(id);
        turmaSalva.setNomeTurma("Turma");

        TurmaDto turmaSalvaDto = new TurmaDto();
        turmaSalvaDto.setId(id);
        turmaSalvaDto.setNomeTurma("Turma");

        when(turmaMapper.toEntity(turmaDto)).thenReturn(turmaAtualizada);
        when(turmaService.atualizar(id, turmaAtualizadaDto)).thenReturn(turmaSalva);
        when(turmaMapper.toDto(turmaSalva)).thenReturn(turmaSalvaDto);

        ResponseEntity<TurmaDto> response = turmaController.atualizar(id, turmaDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Turma", response.getBody().getNomeTurma());
    }

    @Test
    void excluir() {
        ResponseEntity<Void> response = turmaController.excluir(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(turmaService, times(1)).excluir(1L);
    }

    @Test
    void pegarDocentesPorMateriasDoCurso() {
        Long idCurso = 1L;
        Curso curso = new Curso();
        curso.setId(idCurso);

        Materia materia1 = new Materia();
        Materia materia2 = new Materia();

        Docente docente1 = new Docente();
        docente1.setId(1L);
        docente1.setNomeCompleto("Docente 1");

        Docente docente2 = new Docente();
        docente2.setId(2L);
        docente2.setNomeCompleto("Docente 2");

        materia1.setDocentes(List.of(docente1));
        materia2.setDocentes(List.of(docente2));

        curso.setMaterias(List.of(materia1, materia2));

        DocenteSimplifiedDto docenteSimplifiedDto1 = new DocenteSimplifiedDto();
        docenteSimplifiedDto1.setId(1L);
        docenteSimplifiedDto1.setNomeCompleto("Docente 1");

        DocenteSimplifiedDto docenteSimplifiedDto2 = new DocenteSimplifiedDto();
        docenteSimplifiedDto2.setId(2L);
        docenteSimplifiedDto2.setNomeCompleto("Docente 2");

        List<DocenteSimplifiedDto> docenteSimplifiedDtoList = List.of(docenteSimplifiedDto1, docenteSimplifiedDto2);

        when(cursoService.obter(idCurso)).thenReturn(curso);
        when(docenteMapper.toSimplifiedDto(anyList())).thenReturn(docenteSimplifiedDtoList);

        ResponseEntity<List<DocenteSimplifiedDto>> response = turmaController.pegarDocentesPorMateriasDoCurso(idCurso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Docente 1", response.getBody().get(0).getNomeCompleto());
        assertEquals("Docente 2", response.getBody().get(1).getNomeCompleto());
    }

    @Test
    void pegarCursosPorMateriasDoDocente() {
        Long idDocente = 1L;
        Docente docente = new Docente();
        docente.setId(idDocente);

        Materia materia1 = new Materia();
        Materia materia2 = new Materia();

        Curso curso1 = new Curso();
        curso1.setId(1L);
        curso1.setNome("Curso 1");

        Curso curso2 = new Curso();
        curso2.setId(2L);
        curso2.setNome("Curso 2");

        materia1.setCursos(List.of(curso1));
        materia2.setCursos(List.of(curso2));

        docente.setMaterias(List.of(materia1, materia2));

        when(docenteService.obter(idDocente)).thenReturn(docente);

        ResponseEntity<List<Curso>> response = turmaController.pegarCursosPorMateriasDoDocente(idDocente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().stream().anyMatch(curso -> curso.getNome().equals("Curso 1")));
        assertTrue(response.getBody().stream().anyMatch(curso -> curso.getNome().equals("Curso 2")));
    }

    @Test
    void pegarTurmasPorDocente() {
        Long idDocente = 1L;

        TurmaDto turma1 = new TurmaDto();
        turma1.setId(1L);
        turma1.setNomeTurma("Turma 1");

        TurmaDto turma2 = new TurmaDto();
        turma2.setId(2L);
        turma2.setNomeTurma("Turma 2");

        List<TurmaDto> turmas = List.of(turma1, turma2);

        when(turmaService.obterTurmasPorDocente(idDocente)).thenReturn(turmas);

        ResponseEntity<List<TurmaDto>> response = turmaController.pegarTurmasPorDocente(idDocente);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Turma 1", response.getBody().get(0).getNomeTurma());
        assertEquals("Turma 2", response.getBody().get(1).getNomeTurma());
    }
}