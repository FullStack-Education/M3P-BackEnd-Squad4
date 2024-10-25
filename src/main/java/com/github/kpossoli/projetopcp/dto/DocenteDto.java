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
	private String nome;

	@NotBlank
	private String genero;

	@NotNull
	private LocalDate dataNascimento;

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
	private List<MateriaDto> materias;

	@NotBlank
	private String cep;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;

	@NotBlank
	private String logradouro;

	@NotNull
	private Long numero;

	private String complemento;

	@NotBlank
	private String bairro;

	private String pontoDeReferencia;

	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private UsuarioDto usuario;

}
