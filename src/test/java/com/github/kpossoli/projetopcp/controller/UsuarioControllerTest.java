package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.dto.UsuarioDto;
import com.github.kpossoli.projetopcp.dto.UsuarioSimplifiedDto;
import com.github.kpossoli.projetopcp.mapper.UsuarioMapper;
import com.github.kpossoli.projetopcp.model.Papel;
import com.github.kpossoli.projetopcp.model.Usuario;
import com.github.kpossoli.projetopcp.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioController usuarioController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario() {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome("usuario");
        usuarioDto.setEmail("usuario@mail.com");
        usuarioDto.setSenha("12345");

        Usuario usuario = new Usuario();
        usuario.setNome("usuario");
        usuario.setEmail("usuario@mail.com");
        usuario.setSenha("12345");

        Usuario usuarioSalvo = new Usuario();
        usuarioSalvo.setId(1L);
        usuarioSalvo.setNome("usuario");
        usuarioSalvo.setEmail("usuario@mail.com");
        usuarioSalvo.setSenha("12345");

        UsuarioDto usuarioSalvoDto = new UsuarioDto();
        usuarioSalvoDto.setId(1L);
        usuarioSalvoDto.setNome("usuario");
        usuarioSalvoDto.setEmail("usuario@mail.com");
        usuarioSalvoDto.setSenha(null);

        when(usuarioMapper.toEntity(usuarioDto)).thenReturn(usuario);
        when(usuarioService.criarUsuario(usuario)).thenReturn(usuarioSalvo);
        when(usuarioMapper.toDto(usuarioSalvo)).thenReturn(usuarioSalvoDto);

        ResponseEntity<UsuarioDto> response = usuarioController.criarUsuario(usuarioDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("usuario@mail.com", response.getBody().getEmail());
        assertEquals("usuario", response.getBody().getNome());
        assertNull(response.getBody().getSenha());

        verify(usuarioMapper).toEntity(usuarioDto);
        verify(usuarioService).criarUsuario(usuario);
        verify(usuarioMapper).toDto(usuarioSalvo);
    }

    @Test
    void obterByEmail() {
        String email = "usuario@mail.com";

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNome("usuario");
        usuario.setEmail(email);
        usuario.setSenha("senha123");

        UsuarioSimplifiedDto usuarioSimplifiedDto = new UsuarioSimplifiedDto();
        usuarioSimplifiedDto.setId(1L);
        usuarioSimplifiedDto.setNome("usuario");
        usuarioSimplifiedDto.setEmail(email);
        usuarioSimplifiedDto.setPapel("ALUNO");

        when(usuarioService.obterByEmail(email)).thenReturn(usuario);
        when(usuarioMapper.toSimpliedDto(usuario)).thenReturn(usuarioSimplifiedDto);

        ResponseEntity<UsuarioSimplifiedDto> response = usuarioController.obterByEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(email, response.getBody().getEmail());
        assertEquals("usuario", response.getBody().getNome());
        assertEquals("ALUNO", response.getBody().getPapel());

        verify(usuarioService).obterByEmail(email);
        verify(usuarioMapper).toSimpliedDto(usuario);
    }
}