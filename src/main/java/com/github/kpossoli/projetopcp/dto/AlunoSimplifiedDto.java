package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlunoSimplifiedDto {

    private Long id;

    @NotBlank
    private String nomeCompleto;

    @NotBlank
    private String email;

}
