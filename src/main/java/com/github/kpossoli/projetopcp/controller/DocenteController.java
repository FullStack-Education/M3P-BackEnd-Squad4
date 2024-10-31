package com.github.kpossoli.projetopcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.DocenteDto;
import com.github.kpossoli.projetopcp.mapper.DocenteMapper;
import com.github.kpossoli.projetopcp.model.Docente;
import com.github.kpossoli.projetopcp.service.DocenteService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/docentes")
public class DocenteController {

    private final DocenteService docenteService;
    private final DocenteMapper docenteMapper;

    @Operation(summary = "Realiza a busca do Docente pelo ID ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Docente encontrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 2,\n" +
                                            "  \"nomeCompleto\": \"Prof. Pedro H\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"nascimento\": \"1993-05-12\",\n" +
                                            "  \"naturalidade\": \"Florianópolis-SC\",\n" +
                                            "  \"cpf\": \"99999999999\",\n" +
                                            "  \"rg\": \"9999999-SSP/SC\",\n" +
                                            "  \"telefone\": \"99999999999\",\n" +
                                            "  \"email\": \"pedrodmh@mail.com\",\n" +
                                            "  \"senha\": \"1234\",\n" +
                                            "  \"estadoCivil\": \"Casado\",\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2 },\n" +
                                            "    { \"id\": 3 }\n" +
                                            "  ],\n" +
                                            "  \"cep\": \"88888888\",\n" +
                                            "  \"localidade\": \"Florianópolis\",\n" +
                                            "  \"uf\": \"Santa Catarina\",\n" +
                                            "  \"logradouro\": \"Rua dos Manezinhos\",\n" +
                                            "  \"numero\": 100,\n" +
                                            "  \"complemento\": \"Não há\",\n" +
                                            "  \"bairro\": \"Centro\",\n" +
                                            "  \"referencia\": \"Não há\"\n" +
                                            "}"
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
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('DOCENTE_READ')")
    public ResponseEntity<DocenteDto> obter(@PathVariable Long id) {
        Docente docente = docenteService.obter(id);
        DocenteDto docenteDto = docenteMapper.toDto(docente);

        return ResponseEntity.ok(docenteDto);
    }

    @Operation(summary = "Retorna todos os Docentes cadastrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Docentes encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n"+ "{\n" +
                                            "  \"id\": 2,\n" +
                                            "  \"nomeCompleto\": \"Prof. Castro de Castro\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"nascimento\": \"1993-05-12\",\n" +
                                            "  \"naturalidade\": \"Florianópolis-SC\",\n" +
                                            "  \"cpf\": \"99999999999\",\n" +
                                            "  \"rg\": \"9999999-SSP/SC\",\n" +
                                            "  \"telefone\": \"99999999999\",\n" +
                                            "  \"email\": \"mcastrodcastro@mail.com\",\n" +
                                            "  \"senha\": \"1234\",\n" +
                                            "  \"estadoCivil\": \"Casado\",\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2 },\n" +
                                            "    { \"id\": 3 }\n" +
                                            "  ],\n" +
                                            "  \"cep\": \"88888888\",\n" +
                                            "  \"localidade\": \"Florianópolis\",\n" +
                                            "  \"uf\": \"Santa Catarina\",\n" +
                                            "  \"logradouro\": \"Rua dos Manezinhos\",\n" +
                                            "  \"numero\": 100,\n" +
                                            "  \"complemento\": \"Não há\",\n" +
                                            "  \"bairro\": \"Centro\",\n" +
                                            "  \"referencia\": \"Não há\"\n" +
                                            "}\n" + "]"
                            )
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Não há docentes cadastrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping
    @PreAuthorize("hasAuthority('DOCENTE_READ')")
    public ResponseEntity<List<DocenteDto>> listar() {
        List<Docente> docentes = docenteService.listar();
        List<DocenteDto> docentesDto = docenteMapper.toDto(docentes);

        return ResponseEntity.ok(docentesDto);
    }

    @Operation(summary = "Cadastra Docente no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Docentes criado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 2,\n" +
                                            "  \"nomeCompleto\": \"Prof. Matheus R\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"nascimento\": \"1993-05-12\",\n" +
                                            "  \"naturalidade\": \"Florianópolis-SC\",\n" +
                                            "  \"cpf\": \"99999999999\",\n" +
                                            "  \"rg\": \"9999999-SSP/SC\",\n" +
                                            "  \"telefone\": \"99999999999\",\n" +
                                            "  \"email\": \"mrs@mail.com\",\n" +
                                            "  \"senha\": \"1234\",\n" +
                                            "  \"estadoCivil\": \"Casado\",\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2 },\n" +
                                            "    { \"id\": 3 }\n" +
                                            "  ],\n" +
                                            "  \"cep\": \"88888888\",\n" +
                                            "  \"localidade\": \"Florianópolis\",\n" +
                                            "  \"uf\": \"Santa Catarina\",\n" +
                                            "  \"logradouro\": \"Rua dos Manezinhos\",\n" +
                                            "  \"numero\": 100,\n" +
                                            "  \"complemento\": \"Não há\",\n" +
                                            "  \"bairro\": \"Centro\",\n" +
                                            "  \"referencia\": \"Não há\"\n" +
                                            "}\n"
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
    @PreAuthorize("hasAuthority('DOCENTE_WRITE')")
    public ResponseEntity<DocenteDto> criar(@RequestBody @Valid DocenteDto docenteDto) {
        Docente docente = docenteMapper.toEntity(docenteDto);
        Docente docenteSalvo = docenteService.criar(docente);
        DocenteDto docenteSalvoDto = docenteMapper.toDto(docenteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(docenteSalvoDto);
    }

    @Operation(summary = "Atualiza o Docente pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Docentes atualizado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 2,\n" +
                                            "  \"nomeCompleto\": \"Prof. Caio Pizza\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"nascimento\": \"1993-05-12\",\n" +
                                            "  \"naturalidade\": \"Florianópolis-SC\",\n" +
                                            "  \"cpf\": \"99999999999\",\n" +
                                            "  \"rg\": \"9999999-SSP/SC\",\n" +
                                            "  \"telefone\": \"99999999999\",\n" +
                                            "  \"email\": \"cnpizza@mail.com\",\n" +
                                            "  \"senha\": \"1234\",\n" +
                                            "  \"estadoCivil\": \"Casado\",\n" +
                                            "  \"materias\": [\n" +
                                            "    { \"id\": 2 },\n" +
                                            "    { \"id\": 3 }\n" +
                                            "  ],\n" +
                                            "  \"cep\": \"88888888\",\n" +
                                            "  \"localidade\": \"Florianópolis\",\n" +
                                            "  \"uf\": \"Santa Catarina\",\n" +
                                            "  \"logradouro\": \"Rua dos Manezinhos\",\n" +
                                            "  \"numero\": 100,\n" +
                                            "  \"complemento\": \"Não há\",\n" +
                                            "  \"bairro\": \"Centro\",\n" +
                                            "  \"referencia\": \"Não há\"\n" +
                                            "}\n"
                            ))),
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
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('DOCENTE_WRITE')")
    public ResponseEntity<DocenteDto> atualizar(@PathVariable Long id, @RequestBody @Valid DocenteDto docenteDto) {
        Docente docente = docenteMapper.toEntity(docenteDto);
        Docente docenteSalvo = docenteService.atualizar(id, docente);
        DocenteDto docenteSalvoDto = docenteMapper.toDto(docenteSalvo);

        return ResponseEntity.ok(docenteSalvoDto);
    }

    @Operation(summary = "Exclui Docentes pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Docentes excluido com sucesso.",
                    content = @Content
            ),
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DOCENTE_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        docenteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}

