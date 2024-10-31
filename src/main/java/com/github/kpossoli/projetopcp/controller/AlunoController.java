package com.github.kpossoli.projetopcp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import com.github.kpossoli.projetopcp.dto.AlunoDto;
import com.github.kpossoli.projetopcp.dto.NotaDto;
import com.github.kpossoli.projetopcp.mapper.AlunoMapper;
import com.github.kpossoli.projetopcp.mapper.NotaMapper;
import com.github.kpossoli.projetopcp.model.Aluno;
import com.github.kpossoli.projetopcp.model.Nota;
import com.github.kpossoli.projetopcp.model.Pontuacao;
import com.github.kpossoli.projetopcp.service.AlunoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;
    private final AlunoMapper alunoMapper;
    private final NotaMapper notaMapper;

    @Operation(summary = "Retorna todos os Alunos cadastrados ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alunos encontrados com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "[\n" +"{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeCompleto\": \"Rafael Pizza\",\n" +
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
            @ApiResponse(responseCode = "404", description = "Não há Alunos cadastrados",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping
    @PreAuthorize("hasAuthority('ALUNO_READ')")
    public ResponseEntity<List<AlunoDto>> listar() {
        List<Aluno> alunos = alunoService.listar();
        List<AlunoDto> alunosDto = alunoMapper.toDto(alunos);

        return ResponseEntity.ok(alunosDto);
    }

    @Operation(summary = "Realiza a busca do Aluno pelo ID ", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno encontrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeCompleto\": \"Douglas\",\n" +
                                            "  \"telefone\": \"(11) 98765-4321\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"turma\": {\n" +
                                            "    \"id\": 5\n" +
                                            "  },\n" +
                                            "  \"nascimento\": \"1990-04-23\",\n" +
                                            "  \"email\": \"dopizza@mail.com\",\n" +
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
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_READ')")
    public ResponseEntity<AlunoDto> obter(@PathVariable Long id) {
        Aluno aluno = alunoService.obter(id);
        AlunoDto alunoDto = alunoMapper.toDto(aluno);

        return ResponseEntity.ok(alunoDto);
    }

    @Operation(summary = "Cadastra Aluno no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeCompleto\": \"Gabriel Pizza\",\n" +
                                            "  \"telefone\": \"(11) 98765-4321\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"turma\": {\n" +
                                            "    \"id\": 5\n" +
                                            "  },\n" +
                                            "  \"nascimento\": \"1990-04-23\",\n" +
                                            "  \"email\": \"gabron@mail.com\",\n" +
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
    @PreAuthorize("hasAuthority('ALUNO_WRITE')")
    public ResponseEntity<AlunoDto> criar(@RequestBody @Valid AlunoDto alunoDto) {
        Aluno aluno = alunoMapper.toEntity(alunoDto);
        Aluno alunoSalvo = alunoService.criar(aluno);
        AlunoDto alunoSalvoDto = alunoMapper.toDto(alunoSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvoDto);
    }

    @Operation(summary = "Atualiza o Aluno pelo ID", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{\n" +
                                            "  \"id\": 1,\n" +
                                            "  \"nomeCompleto\": \"Guilherme A\",\n" +
                                            "  \"telefone\": \"(11) 98765-4321\",\n" +
                                            "  \"genero\": \"Masculino\",\n" +
                                            "  \"turma\": {\n" +
                                            "    \"id\": 5\n" +
                                            "  },\n" +
                                            "  \"nascimento\": \"1990-04-23\",\n" +
                                            "  \"email\": \"guialvez@mail.com\",\n" +
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
                                            "}"
                            ))),
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
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_WRITE')")
    public ResponseEntity<AlunoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AlunoDto alunoDto) {
        Aluno aluno = alunoMapper.toEntity(alunoDto);
        Aluno alunoSalvo = alunoService.atualizar(id, aluno);
        AlunoDto alunoSalvoDto = alunoMapper.toDto(alunoSalvo);

        return ResponseEntity.ok(alunoSalvoDto);
    }

    @Operation(summary = "Exclui Aluno pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno excluido com sucesso.",
                    content = @Content
            ),
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
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ALUNO_DELETE')")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        alunoService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Exclui Docentes pelo ID", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pontuação calculada com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"pontuacao\": \"10\" }")
                    )
            ),
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
    @GetMapping("/{id}/pontuacao")
    @PreAuthorize("hasAuthority('PONTUACAO_READ')")
    public ResponseEntity<Pontuacao> obterPontuacao(@PathVariable Long id) {
        Pontuacao pontuacao = alunoService.obterPontuacao(id);

        return ResponseEntity.ok(pontuacao);
    }

    @Operation(summary = "Realiza a busca das Notas do Aluno pelo ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Notas do Aluno encontradas com sucesso.",
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
    @GetMapping("/{id}/notas")
    @PreAuthorize("hasAuthority('NOTA_READ')")
    public ResponseEntity<List<NotaDto>> listar(@PathVariable Long id) {
        List<Nota> notas = alunoService.notas(id);
        List<NotaDto> alunosDto = notaMapper.toDto(notas);

        return ResponseEntity.ok(alunosDto);
    }


}


