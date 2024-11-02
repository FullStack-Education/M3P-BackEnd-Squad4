package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import com.github.kpossoli.projetopcp.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {

    private final AlunoService alunoService;
    private final DocenteService docenteService;
    private final TurmaService turmaService;

    @Operation(summary = "Retorna a quantidade de Alunos, Docentes, Turmas cadastrados no sistema", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados retornados com sucesso!",
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(value = "{\"quantidadeDeAlunos\": 50, \"quantidadeDeDocentes\": 10, \"quantidadeDeTurmas\": 5}")
            )),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas. O usuário não está autorizado a acessar o sistema.",
                    content = @Content
            ),
            @ApiResponse(responseCode = "400", description = "Requisição inválida, dados ausentes ou incorretos.",
                    content = @Content
            )
    })
    @GetMapping()
    @PreAuthorize("hasAuthority('ESTATISTICAS_READ')")
    public ResponseEntity<Map<String, Integer>> getEstatisticas(){
        int quantidadeDeAlunos = alunoService.listar().size();
        int quantidadeDeDocentes = docenteService.listar().size();
        int quantidadeDeTurmas = turmaService.listar().size();

        Map<String, Integer> estatisticas = new HashMap<>();
        estatisticas.put("quantidadeDeAlunos", quantidadeDeAlunos);
        estatisticas.put("quantidadeDeDocentes", quantidadeDeDocentes);
        estatisticas.put("quantidadeDeTurmas", quantidadeDeTurmas);

        return ResponseEntity.ok(estatisticas);
    }
}
