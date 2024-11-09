package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.dto.TurmaDto;
import com.github.kpossoli.projetopcp.model.*;
import com.github.kpossoli.projetopcp.repository.AlunoRepository;
import com.github.kpossoli.projetopcp.repository.TurmaRepository;
import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import com.github.kpossoli.projetopcp.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.service.NotaService;

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
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;
    private final TurmaService turmaService;
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;
    private final DocenteService docenteService;
    private final AlunoService alunoService;
    private final NotaMapper notaMapper;
    @Autowired
    private EntityManager entityManager;

    @Operation(summary = "Realiza a busca da Nota pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota encontrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"valor\": 9.5,\n" +
                                            "  \"data\": \"2024-10-15\",\n" +
                                            "  \"aluno\": {\n" +
                                            "    \"id\": 3\n" +
                                            "  },\n" +
                                            "  \"docente\": {\n" +
                                            "    \"id\": 2\n" +
                                            "  },\n" +
                                            "  \"materia\": {\n" +
                                            "    \"id\": 4\n" +
                                            "  }\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<NotaDto> obter(@PathVariable Long id) {
        Nota nota = notaService.obter(id);
        NotaDto notaDto = notaMapper.toDto(nota);

        return ResponseEntity.ok(notaDto);
    }

    @Operation(summary = "Retorna todas as Notas cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notas encontradas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+"{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"valor\": 9.5,\n" +
                                            "  \"data\": \"2024-10-15\",\n" +
                                            "  \"aluno\": {\n" +
                                            "    \"id\": 3\n" +
                                            "  },\n" +
                                            "  \"docente\": {\n" +
                                            "    \"id\": 2\n" +
                                            "  },\n" +
                                            "  \"materia\": {\n" +
                                            "    \"id\": 4\n" +
                                            "  }\n" +
                                            "}" + "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há Notas cadastradas",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listar() {
        List<Nota> notas = notaService.listar();
        List<NotaDto> notasDto = notaMapper.toDto(notas);

        return ResponseEntity.ok(notasDto);
    }

    @Operation(summary = "Cadastra Nota no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Nota criada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"valor\": 9.5,\n" +
                                            "  \"data\": \"2024-10-15\",\n" +
                                            "  \"aluno\": {\n" +
                                            "    \"id\": 3\n" +
                                            "  },\n" +
                                            "  \"docente\": {\n" +
                                            "    \"id\": 2\n" +
                                            "  },\n" +
                                            "  \"materia\": {\n" +
                                            "    \"id\": 4\n" +
                                            "  }\n" +
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
    @PreAuthorize("hasAuthority('NOTA_WRITE')")
    public ResponseEntity<NotaDto> criar(@RequestBody @Valid NotaDto notaDto) {
        Nota nota = notaMapper.toEntity(notaDto);
        Nota notaSalvo = notaService.criar(nota);
        NotaDto notaSalvoDto = notaMapper.toDto(notaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(notaSalvoDto);
    }

    @Operation(summary = "Atualiza a Nota pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Nota atualizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"valor\": 9.5,\n" +
                                            "  \"data\": \"2024-10-15\",\n" +
                                            "  \"aluno\": {\n" +
                                            "    \"id\": 3\n" +
                                            "  },\n" +
                                            "  \"docente\": {\n" +
                                            "    \"id\": 2\n" +
                                            "  },\n" +
                                            "  \"materia\": {\n" +
                                            "    \"id\": 4\n" +
                                            "  }\n" +
                                            "}"
                            ))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_WRITE')")
    public ResponseEntity<NotaDto> atualizar(@PathVariable Long id, @RequestBody @Valid NotaDto notaDto) {
        Nota nota = notaMapper.toEntity(notaDto);
        Nota notaSalvo = notaService.atualizar(id, nota);
        NotaDto notaSalvoDto = notaMapper.toDto(notaSalvo);

        return ResponseEntity.ok(notaSalvoDto);
    }

    @Operation(summary = "Exclui Nota pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Nota excluida com sucesso.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Nota não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('NOTA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        notaService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Realiza a busca das Notas do Aluno pelo ID do Aluno", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notas do Aluno encontradas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"valor\": 9.5,\n" +
                                            "  \"data\": \"2024-10-15\",\n" +
                                            "  \"aluno\": {\n" +
                                            "    \"id\": 3\n" +
                                            "  },\n" +
                                            "  \"docente\": {\n" +
                                            "    \"id\": 2\n" +
                                            "  },\n" +
                                            "  \"materia\": {\n" +
                                            "    \"id\": 4\n" +
                                            "  }\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
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

    @Operation(summary = "Realiza a busca das Turmas do Docente pelo ID do Docente", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notas do Aluno encontradas com sucesso.",
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
            @ApiResponse(responseCode = "404", description = "Docente não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @Transactional
    @GetMapping(path = "/docentes/{idDocente}/turmas")
    @PreAuthorize("hasAuthority('TURMA_READ')")
    public ResponseEntity<List<Turma>> getTurmasPorProfessor(@PathVariable Long idDocente) {
        String jpql = "SELECT t FROM Turma t WHERE t.docente.id = :docenteId";
        List<Turma> turmas = entityManager.createQuery(jpql, Turma.class)
                .setParameter("docenteId", idDocente)
                .getResultList();

        return ResponseEntity.ok(turmas);
    }


    @Operation(summary = "Realiza a busca dos Alunos da Turma pelo ID da Turma", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notas do Aluno encontradas com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n" +"{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeCompleto\": \"Enrico\",\n" +
                                            "  \"telefone\": \"(11) 98765-4321\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"turma\": {\n" +
                                            "    \"id\": 5\n" +
                                            "  },\n" +
                                            "  \"nascimento\": \"1990-04-23\",\n" +
                                            "  \"email\": \"rafapizza@mail.com\",\n" +
                                            "  \"senha\": \"senhaSegura123\",\n" +
                                            "  \"cpf\": \"12345678900\",\n" +
                                            "  \"rg\": \"12345678-SSP/SP\",\n" +
                                            "  \"naturalidade\": \"São Paulo-SP\",\n" +
                                            "  \"cep\": \"01001000\",\n" +
                                            "  \"logradouro\": \"Av. Paulista\",\n" +
                                            "  \"numero\": \"1000\",\n" +
                                            "  \"localidade\": \"São Paulo\",\n" +
                                            "  \"uf\": \"SP\",\n" +
                                            "  \"complemento\": \"Apto 101\",\n" +
                                            "  \"usuario\": {\n" +
                                            "    \"id\": 8\n" +
                                            "  }\n" +
                                            "}\n" + "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Turma não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @Transactional
    @GetMapping(path = "/turmas/{idTurma}/alunos")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<Aluno>> getAlunoPorTurma(@PathVariable Long idTurma) {
        String jpql = "SELECT a FROM Aluno a WHERE a.turma.id = :turmaId";
        List<Aluno> alunos = entityManager.createQuery(jpql, Aluno.class)
                .setParameter("turmaId", idTurma)
                .getResultList();

        return ResponseEntity.ok(alunos);
    }
}

