package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.security.Autorizacao;
import com.github.kpossoli.projetopcp.security.JwtService;
import com.github.kpossoli.projetopcp.security.RespostaJWT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void login() {
        Autorizacao authRequestDTO = new Autorizacao();
        authRequestDTO.setEmail("aluno@mail.com");
        authRequestDTO.setSenha("12345");

        Authentication authentication = mock(Authentication.class);
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        when(jwtService.gerarToken("aluno@mail.com")).thenReturn("mocked-jwt-token");

        RespostaJWT resposta = loginController.login(authRequestDTO);

        assertNotNull(resposta);
        assertEquals("mocked-jwt-token", resposta.getAccessToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService).gerarToken("aluno@mail.com");
    }

    @Test
    void loginFalho() {
        Autorizacao authRequestDTO = new Autorizacao();
        authRequestDTO.setEmail("aluno@mail.com");
        authRequestDTO.setSenha("senhaErrada123");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new UsernameNotFoundException("Usuário Não encontrado!"));

        assertThrows(UsernameNotFoundException.class, () -> loginController.login(authRequestDTO));

        verify(jwtService, never()).gerarToken(anyString());
    }
}