package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.CursoSimplifiedDto;
import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Turma;
import com.github.kpossoli.projetopcp.repository.CursoRepository;
import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.crossstore.ChangeSetPersister;
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
import java.util.stream.Collectors;

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
    private final CursoRepository cursoRepository;

    @Operation(summary = "Realiza a busca do Curso pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso encontrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<CursoDto> obter(@PathVariable Long id) {
        Curso curso = cursoService.obter(id);
        CursoDto cursoDto = cursoMapper.toDto(curso);

        return ResponseEntity.ok(cursoDto);
    }

    @Operation(summary = "Retorna todos os Cursos cadastrados / Realiza a busca do Curso pelo ID do Aluno", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "  ]\n" +
                                            "}"+ "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há Cursos cadastrados. / Aluno não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<CursoDto>> listar() {
        List<Curso> cursos = cursoService.listar();
        List<CursoDto> cursosDto = cursoMapper.toDto(cursos);

        return ResponseEntity.ok(cursosDto);
    }

    @Operation(summary = "Cadastra Curso no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "  ]\n" +
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
    @PreAuthorize("hasAuthority('CURSO_WRITE')")
    public ResponseEntity<CursoDto> criar(@RequestBody @Valid CursoDto cursoDto) {
        Curso curso = cursoMapper.toEntity(cursoDto);
        Curso cursoSalvo = cursoService.criar(curso);
        CursoDto cursoSalvoDto = cursoMapper.toDto(cursoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(cursoSalvoDto);
    }

    @Operation(summary = "Atualiza o Curso pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "  ]\n" +
                                            "}"
                            ))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_WRITE')")
    public ResponseEntity<CursoDto> atualizar(@PathVariable Long id, @RequestBody @Valid CursoDto cursoDto) {
        Curso curso = cursoMapper.toEntity(cursoDto);
        Curso cursoSalvo = cursoService.atualizar(id, curso);
        CursoDto cursoSalvoDto = cursoMapper.toDto(cursoSalvo);

        return ResponseEntity.ok(cursoSalvoDto);
    }

    @Operation(summary = "Retorna todos os Cursos cadastrados / Realiza a busca do Curso pelo ID do Aluno", method = "GET")
    @Parameter(name = "idAluno", description = "ID do Aluno: Realiza a busca do Curso em que o Aluno está cadastrado", required = false)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "  ]\n" +
                                            "}"+ "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há Cursos cadastrados. / Aluno não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })

    @GetMapping(params = "idAluno")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<CursoDto> obterCursoDoAluno(@RequestParam Long idAluno) {
        return ResponseEntity.ok(cursoMapper.toDto(alunoService.obter(idAluno).getTurma().getCurso()));
    }

    @Operation(summary = "Retorna todos os Cursos que o Aluno não esta cadastrado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Curso de Ciências da Computação\",\n" +
                                            "  \"turmas\": [\n" +
                                            "    { \"id\": 5, \"nomeTurma\": \"Turma A\" },\n" +
                                            "    { \"id\": 6, \"nomeTurma\": \"Turma B\" }\n" +
                                            "  ],\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2, \"nome\": \"Algoritmos\" },\n" +
                                            "    { \"id\": 3, \"nome\": \"Estruturas de Dados\" }\n" +
                                            "  ]\n" +
                                            "}"+ "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há Cursos que o Aluno não está cadastrados.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping(path = "alunos/{idAluno}/cursos")
    @PreAuthorize("hasAuthority('CURSO_READ')")
    public ResponseEntity<List<CursoSimplifiedDto>> obterCursosExtrasDoAluno(@PathVariable Long idAluno) {

        Aluno aluno = alunoService.obter(idAluno);

        Curso cursosAluno = aluno.getTurma() != null ? aluno.getTurma().getCurso() : null;

        List<Curso> todosCursos = cursoRepository.findAll();

        List<Curso> cursosDisponíveis = todosCursos.stream()
                .filter(curso -> !curso.equals(cursosAluno))
                .collect(Collectors.toList());

        List<CursoSimplifiedDto> cursoSimplifiedDtos = cursoMapper.toSimplifiedDto(cursosDisponíveis);

        return ResponseEntity.ok(cursoSimplifiedDtos);

    }

    @Operation(summary = "Retorna as Materias Pelo Curso e o Docente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materias encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Java\"\n" +
                                            "}" + "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado. / Docente não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("/{idCurso}/docentes/{idDocente}/materias")
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<List<MateriaDto>> pegarMateriasPorCursoEDocente(@PathVariable Long idCurso, @PathVariable Long idDocente) throws ChangeSetPersister.NotFoundException {

        List<MateriaDto> materias = cursoService.obterMateriasPorCursoEDocente(idCurso, idDocente);

        if(materias.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materias);
    }

    @Operation(summary = "Exclui Curso pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso excluido com sucesso.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('CURSO_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        cursoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}