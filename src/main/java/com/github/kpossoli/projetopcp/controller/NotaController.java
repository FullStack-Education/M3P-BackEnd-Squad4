package com.github.kpossoli.projetopcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.service.NotaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class NotaController {

    private final NotaService notaService;
    private final NotaMapper notaMapper;

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
    @GetMapping("/notas/{id}")
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
    @GetMapping("/notas")
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
    @PostMapping("/notas")
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
    @PutMapping("/notas/{id}")
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
    @DeleteMapping("/notas/{id}")
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
    @GetMapping(path = "/alunos/{idAluno}/notas")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listarNotasPorAluno(@PathVariable Long idAluno) {
        List<Nota> notas = notaService.listar();
        List<Nota> notasDoAluno = new java.util.ArrayList<>(List.of());
        for (Nota nota : notas){
            if (nota.getAluno().getId().equals(idAluno)){
                notasDoAluno.add(nota);
            }
        }
        List<NotaDto> notasDoAlunoDTO = notaMapper.toDto(notasDoAluno);
        return  ResponseEntity.ok(notasDoAlunoDTO);
    }
}

