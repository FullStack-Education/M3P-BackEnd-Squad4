package com.github.kpossoli.projetopcp.controller;

import com.github.kpossoli.projetopcp.security.Autorizacao;
import com.github.kpossoli.projetopcp.security.RespostaJWT;
import com.github.kpossoli.projetopcp.security.JwtService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;


@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public RespostaJWT login(@RequestBody Autorizacao authRequestDTO){
        var auth = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsuario(), authRequestDTO.getSenha());
        Authentication authentication = authenticationManager.authenticate(auth);

        if(authentication.isAuthenticated()){
            return RespostaJWT.builder()
                    .accessToken(jwtService.gerarToken(authRequestDTO.getUsuario()))
                    .build();
        } else {
            throw new UsernameNotFoundException("Usuário Não encontrado!");
        }
    }

}