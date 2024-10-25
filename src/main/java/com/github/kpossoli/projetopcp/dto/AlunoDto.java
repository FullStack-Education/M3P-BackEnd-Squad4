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
	private String nome;

	@NotBlank
	private String telefone;

	@NotBlank
	private String genero;

//	@NotEmpty
	private List<TurmaDto> turmas;

	@NotNull
	private LocalDate dataNascimento;

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
	private String rua;

	@NotBlank
	private Long numero;

	@NotBlank
	private String cidade;

	@NotBlank
	private String estado;

	@NotBlank
	private String complemento;

	@NotNull
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private UsuarioDto usuario;

}
