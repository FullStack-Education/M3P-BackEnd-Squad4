package com.github.kpossoli.projetopcp.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TurmaDto {

	private Long id;

	@NotNull
	private String nome;

	@NotNull
	private DocenteDto docente;

	@NotNull
	private List<AlunoDto> alunos;

}
