package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.DocenteSimplifiedDto;
import com.github.kpossoli.projetopcp.mapper.DocenteMapper;
import com.github.kpossoli.projetopcp.model.Curso;
import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.service.CursoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.mapper.TurmaMapper;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.service.TurmaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;
    private final TurmaMapper turmaMapper;
    private final CursoService cursoService;
    private final DocenteService docenteService;
    private final DocenteMapper docenteMapper;

    @Operation(summary = "Realiza a busca da Turma pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma encontrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeTurma\": \"Turma A\",\n" +
                                            "  \"dataInicio\": \"2024-01-15\",\n" +
                                            "  \"dataTermino\": \"2024-06-15\",\n" +
                                            "  \"horario\": \"08:00 - 12:00\",\n" +
                                            "  \"docenteId\": 2,\n" +
                                            "  \"cursoId\": 3\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<TurmaDto> obter(@PathVariable Long id) {
        Turma turma = turmaService.obter(id);
        TurmaDto turmaDto = turmaMapper.toDto(turma);

        return ResponseEntity.ok(turmaDto);
    }

    @Operation(summary = "Retorna todas as Turmas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turmas encontradas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeTurma\": \"Turma B\",\n" +
                                            "  \"dataInicio\": \"2024-01-15\",\n" +
                                            "  \"dataTermino\": \"2024-06-15\",\n" +
                                            "  \"horario\": \"08:00 - 12:00\",\n" +
                                            "  \"docenteId\": 2,\n" +
                                            "  \"cursoId\": 3\n" +
                                            "}" + "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há turmas cadastrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<List<TurmaDto>> listar() {
        List<Turma> turmas = turmaService.listar();
        List<TurmaDto> turmasDto = turmaMapper.toDto(turmas);

        return ResponseEntity.ok(turmasDto);
    }

    @Operation(summary = "Cadastra Turma no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Turma criada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeTurma\": \"Turma C\",\n" +
                                            "  \"dataInicio\": \"2024-01-15\",\n" +
                                            "  \"dataTermino\": \"2024-06-15\",\n" +
                                            "  \"horario\": \"08:00 - 12:00\",\n" +
                                            "  \"docenteId\": 2,\n" +
                                            "  \"cursoId\": 3\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, por exemplo, dados ausentes ou incorretos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 400, \"messages\": [{ \"code\": \"json_parse\", \"message\": \"Mensagem inválida\" }] }"
                            )))
    })
    @PostMapping
    @PreAuthorize("hasAuthority('TURMA_WRITE')")
    public ResponseEntity<TurmaDto> criar(@RequestBody @Valid TurmaDto turmaDto) {
        Turma turma = turmaMapper.toEntity(turmaDto);
        Turma turmaSalvo = turmaService.criar(turma);
        TurmaDto turmaSalvoDto = turmaMapper.toDto(turmaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalvoDto);
    }

    @Operation(summary = "Atualiza a Turma pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Turma atualizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeTurma\": \"Turma D\",\n" +
                                            "  \"dataInicio\": \"2024-01-15\",\n" +
                                            "  \"dataTermino\": \"2024-06-15\",\n" +
                                            "  \"horario\": \"08:00 - 12:00\",\n" +
                                            "  \"docenteId\": 2,\n" +
                                            "  \"cursoId\": 3\n" +
                                            "}"
                            ))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_WRITE')")
    public ResponseEntity<TurmaDto> atualizar(@PathVariable Long id, @RequestBody @Valid TurmaDto turmaDto) {
        Turma turma = turmaMapper.toEntity(turmaDto);
        Turma turmaSalvo = turmaService.atualizar(id, turmaDto);
        TurmaDto turmaSalvoDto = turmaMapper.toDto(turmaSalvo);

        return ResponseEntity.ok(turmaSalvoDto);
    }

    @Operation(summary = "Exclui Turma pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Turma excluida com sucesso.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Turma não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('TURMA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        turmaService.excluir(id);
        
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Retorna todos os Docentes, cadastrados nas Materias do Curso", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docentes encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "1,\n" +"3"+  "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping(path = "/cursos/{idCurso}/docentes")
    @PreAuthorize("hasAuthority('DOCENTE_READ')")
    public ResponseEntity<List<DocenteSimplifiedDto>> pegarDocentesPorMateriasDoCurso(@PathVariable Long idCurso) {

        Curso curso = cursoService.obter(idCurso);

        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        List<Materia> materias = curso.getMaterias();
        Set<Docente> docentesDoCurso = new HashSet<>();

        for (Materia materia: materias){
            docentesDoCurso.addAll(materia.getDocentes());
        }

        List<DocenteSimplifiedDto> responseDocentesDoCurso = docenteMapper.toSimplifiedDto(new ArrayList<>(docentesDoCurso));

        return ResponseEntity.ok(responseDocentesDoCurso);
    }

    @Operation(summary = "Retorna todos os Cursos, que o Docente está relacionado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n" +
                                            "    {\n" +
                                            "        \"id\": 3,\n" +
                                            "        \"nome\": \"Engenharia de Software\"\n" +
                                            "    }\n" +
                                            "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Docente não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping(path = "/docentes/{idDocente}/cursos")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<Curso>> pegarCursosPorMateriasDoDocente(@PathVariable Long idDocente) {

        Docente docente = docenteService.obter(idDocente);

        if (docente == null) {
            return ResponseEntity.notFound().build();
        }

        List<Materia> materias = docente.getMaterias();
        Set<Curso> cursosDoDocente = new HashSet<>();

        for (Materia materia: materias){
            cursosDoDocente.addAll(materia.getCursos());
        }
        return ResponseEntity.ok(new ArrayList<>(cursosDoDocente));
    }
    @GetMapping("docentes/{idDocente}/turmas")
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<List<TurmaDto>> pegarTurmasPorDocente(@PathVariable Long idDocente) {

        List<TurmaDto> turmas = turmaService.obterTurmasPorDocente(idDocente);

        if(turmas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(turmas);

    }

}

