package com.github.kpossoli.projetopcp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PapelDto {

	@NotNull
	private Long id;

	private String nome;

}
