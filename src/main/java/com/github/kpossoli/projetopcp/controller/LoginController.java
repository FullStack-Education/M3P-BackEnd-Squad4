package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.security.Autorizacao;
import com.github.kpossoli.projetopcp.security.RespostaJWT;
import com.github.kpossoli.projetopcp.security.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Gera um token de login ao usuario no sistema", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido. Retorna um token JWT (JSON Web Token) no corpo da resposta.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{ \"accessToken\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...\" }")
            )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    example = "{ \"error\": \"Unauthorized\", \"message\": \"Credenciais inválidas.\" }"
            ))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados ausentes ou incorretos.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"status\": 400, \"messages\": [{ \"code\": \"login\", \"message\": \"Usuário ou senha inválidos\" }] }"
            )))
    })
    @PostMapping
    public RespostaJWT login(@RequestBody Autorizacao authRequestDTO){
        var auth = new UsernamePasswordAuthenticationToken(authRequestDTO.getEmail(), authRequestDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(auth);

        if(authentication.isAuthenticated()){
            return RespostaJWT.builder()
                    .accessToken(jwtService.gerarToken(authRequestDTO.getEmail()))
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário Não encontrado!");
        }
    }
}