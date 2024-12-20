package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class DocenteSimplifiedDto {

    private Long id;

    @NotBlank
    private String nomeCompleto;

}
