package com.github.kpossoli.projetopcp.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DocenteDto {

	private Long id;

	@NotBlank
	private String nomeCompleto;

	@NotBlank
	private String genero;

	@NotNull
	private LocalDate nascimento;

	@NotBlank
	private String naturalidade;

	@NotBlank
	private String cpf;

	@NotBlank
	private String rg;

	@NotBlank
	private String telefone;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

	@NotBlank
	private String estadoCivil;

	@NotEmpty
	private List<Long> materias;
	
	private String cep;

	private String localidade;

	private String uf;

	private String logradouro;

	private String numero;

	private String complemento;

	private String bairro;

	private String referencia;

}
