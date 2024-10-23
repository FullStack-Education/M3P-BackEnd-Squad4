package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.service.AlunoService;
import com.github.kpossoli.projetopcp.service.DocenteService;
import com.github.kpossoli.projetopcp.service.TurmaService;
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
