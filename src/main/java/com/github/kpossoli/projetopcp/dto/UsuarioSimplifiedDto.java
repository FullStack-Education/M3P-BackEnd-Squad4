package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioSimplifiedDto {


    private Long id;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @Valid
    private String papel;
}
