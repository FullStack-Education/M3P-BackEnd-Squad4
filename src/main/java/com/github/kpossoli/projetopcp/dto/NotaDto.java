package com.github.kpossoli.projetopcp.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
	private Long aluno;

	@NotNull
	private Long docente;

	@NotNull
	private Long materia;

}
