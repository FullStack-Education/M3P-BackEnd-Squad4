package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DocenteSimplifiedDto {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String email;

    @NotBlank
    private String telefone;


}
