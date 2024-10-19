package com.github.kpossoli.projetopcp.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CursoDto {

	private Long id;

	@NotEmpty
	private String nome;

	@NotEmpty
	private List<MateriaDto> materias;

	@NotEmpty
	private List<TurmaDto> turmas;

}
