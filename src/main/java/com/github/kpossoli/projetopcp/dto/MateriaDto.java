package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MateriaDto {

	private Long id;

	@NotNull
	private String nome;

	private Long cursoId;

}
