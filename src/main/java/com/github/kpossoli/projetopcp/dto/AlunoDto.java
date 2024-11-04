package com.github.kpossoli.projetopcp.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
	private Long turma;

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


	private String cep;


	private String logradouro;


	private String numero;


	private String localidade;

	private String bairro;


	private String uf;


	private String complemento;


	private String referencia;

}
