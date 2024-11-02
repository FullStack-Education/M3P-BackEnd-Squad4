package com.github.kpossoli.projetopcp.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlunoDto {

	private Long id;

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	private String telefone;

	@NotBlank
	private String genero;

	@NotNull
	private TurmaDto turma;

	@NotNull
	private LocalDate nascimento;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@NotBlank
	private String cpf;

	@NotBlank
	private String rg;

	@NotBlank
	private String naturalidade;

	@NotBlank
	private String cep;

	@NotBlank
	private String logradouro;

	@NotBlank
	private String numero;

	@NotBlank
	private String localidade;

	@NotBlank
	private String uf;

	@NotBlank
	private String complemento;

}
