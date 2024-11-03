package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TurmaDto {

	private Long id;

	@NotNull
	private String nomeTurma;

	@NotNull
	private LocalDate dataInicio;

	@NotNull
	private LocalDate dataTermino;

	@NotNull
	private String horario;

	private Long docenteId;

	@NotNull
	private Long cursoId;

}
