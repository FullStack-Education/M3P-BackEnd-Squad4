package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.UsuarioDto;
import com.github.kpossoli.projetopcp.dto.UsuarioSimplifiedDto;
import com.github.kpossoli.projetopcp.mapper.UsuarioMapper;
import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @Operation(summary = "Permite que um novo usuário seja cadastrado no sistema.", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"id\": 123, \"nome\": \"Isaac A\", \"email\": \"isaacads@email.com\", \"papel\": { \"id\": 5, \"nome\": \"ALUNO\" } }")
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados ausentes ou incorretos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 400, \"messages\": [{ \"code\": \"json_parse\", \"message\": \"Mensagem inválida\" }] }"
                            )))
    })
    @PostMapping(path = "cadastro")
    @PreAuthorize("hasAuthority('USUARIO_WRITE')")
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);
        UsuarioDto usuarioSalvoDto = usuarioMapper.toDto(usuarioSalvo);
        usuarioSalvoDto.setSenha(null);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvoDto);
    }

    @Operation(summary = "Realiza a busca do usuário pelo email", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"id\": 123, \"email\": \"fredericobk@email.com\", \"papel\": \"ALUNO\" } }")
                    )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 404, \"messages\": [{ \"code\": \"not-found\", \"message\": \"Recurso não encontrado\" }] }"
                            )))
    })
    @GetMapping("/buscar")
    @PreAuthorize("hasAuthority('USUARIO_READ')")
    public ResponseEntity<UsuarioSimplifiedDto> obterByEmail(@RequestParam String email) {
        Usuario usuario = usuarioService.obterByEmail(email);
        UsuarioSimplifiedDto usuarioSimplifiedDto = usuarioMapper.toSimpliedDto(usuario);

        return ResponseEntity.ok(usuarioSimplifiedDto);
    }

}