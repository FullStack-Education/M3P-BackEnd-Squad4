package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.UsuarioDto;
import com.github.kpossoli.projetopcp.mapper.UsuarioMapper;
import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cadastro")
public class CadastroController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    @PostMapping
    @PreAuthorize("hasAuthority('USUARIO_WRITE')")
    public ResponseEntity<UsuarioDto> criarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDto);
        Usuario usuarioSalvo = usuarioService.criarUsuario(usuario);
        UsuarioDto usuarioSalvoDto = usuarioMapper.toDto(usuarioSalvo);
        usuarioSalvoDto.setSenha(null);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvoDto);
    }

}