package com.github.kpossoli.projetopcp.dto;

import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@Data
public class UsuarioDto {

	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@Valid
	private PapelDto papel;

}
