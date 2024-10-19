package com.github.kpossoli.projetopcp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotaDto {

	private Long id;

	@NotNull
	private BigDecimal valor;

	@NotNull
	private LocalDate data;

	@NotNull
	private AlunoDto aluno;

	@NotNull
	private DocenteDto docente;

	@NotNull
	private MateriaDto materia;

}
