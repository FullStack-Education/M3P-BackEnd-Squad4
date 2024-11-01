package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.MateriaDto;
import com.github.kpossoli.projetopcp.mapper.MateriaMapper;
import com.github.kpossoli.projetopcp.model.Materia;
import com.github.kpossoli.projetopcp.service.MateriaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class MateriaController {

    private final MateriaService materiaService;
    private final MateriaMapper materiaMapper;
    private final CursoService cursoService;

    @Operation(summary = "Realiza a busca da Materia pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Materia encontrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Java\"\n" +
                                            "}"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Materia não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("materias/{id}")
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<MateriaDto> obter(@PathVariable Long id) {
        Materia materia = materiaService.obter(id);
        MateriaDto materiaDto = materiaMapper.toDto(materia);

        return ResponseEntity.ok(materiaDto);
    }

    @Operation(summary = "Retorna todas as Materia cadastradas", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Materia encontradas com sucesso.",
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
            @ApiResponse(responseCode = "404", description = "Não há Materia cadastrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("materias")
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<List<MateriaDto>> listar() {
        List<Materia> materias = materiaService.listar();
        List<MateriaDto> materiasDto = materiaMapper.toDto(materias);

        return ResponseEntity.ok(materiasDto);
    }

    @Operation(summary = "Realiza a busca da Materia pelo ID do Curso", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Materia encontrada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Java\"\n" +
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
    @GetMapping("cursos/{id}/materias")
    @PreAuthorize("hasAuthority('MATERIA_READ')")
    public ResponseEntity<List<MateriaDto>> listarMateriasPorCurso(@PathVariable("id") Long idCurso) {
        List<Materia> materias = materiaService.listarMateriasPorCurso(idCurso);
        List<MateriaDto> materiasDto = materiaMapper.toDto(materias);

        return ResponseEntity.ok(materiasDto);
    }

    @Operation(summary = "Cadastra Materia no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Materia criada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Java\"\n" +
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
    @PostMapping("materias")
    @PreAuthorize("hasAuthority('MATERIA_WRITE')")
    public ResponseEntity<MateriaDto> criar(@RequestBody @Valid MateriaDto materiaDto) {
        Materia materia = materiaMapper.toEntity(materiaDto);
        Materia materiaSalvo = materiaService.criar(materia);
        MateriaDto materiaSalvoDto = materiaMapper.toDto(materiaSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(materiaSalvoDto);
    }

    @Operation(summary = "Atualiza a Materia pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Materia atualizada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nome\": \"Java\"\n" +
                                            "}"
                            ))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Materia não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @PutMapping("materias/{id}")
    @PreAuthorize("hasAuthority('MATERIA_WRITE')")
    public ResponseEntity<MateriaDto> atualizar(@PathVariable Long id, @RequestBody @Valid MateriaDto materiaDto) {
        Materia materia = materiaMapper.toEntity(materiaDto);
        Materia materiaSalvo = materiaService.atualizar(id, materia);
        MateriaDto materiaSalvoDto = materiaMapper.toDto(materiaSalvo);

        return ResponseEntity.ok(materiaSalvoDto);
    }

    @Operation(summary = "Exclui Materia pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Materia excluida com sucesso.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Materia não encontrada.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @DeleteMapping("materias/{id}")
    @PreAuthorize("hasAuthority('MATERIA_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        materiaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}

