package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CursoSimplifiedDto {

    private Long id;

    @NotEmpty
    private String nome;

}
