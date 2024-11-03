package com.github.kpossoli.projetopcp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(nullable = false)
	@Getter @Setter
	private String nomeCompleto;

	@Getter @Setter
	private String telefone;

	@Getter @Setter
	private String genero;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "id_turma")
	private Turma turma;

	@Column(name = "data_nascimento")
	@Getter @Setter
	private LocalDate nascimento;

	@Column(unique = true)
	@Getter @Setter
	private String email;

	@Getter @Setter
	private String senha;

	@Getter @Setter
	private String cpf;

	@Getter @Setter
	private String rg;

	@Getter @Setter
	private String naturalidade;

	@Getter @Setter
	private String cep;

	@Getter @Setter
	private String logradouro;

	@Getter @Setter
	private String numero;

	@Getter @Setter
	private String localidade;

	@Getter @Setter
	private String bairro;

	@Getter @Setter
	private String uf;

	@Getter @Setter
	private String complemento;

	@Getter @Setter
	private String referencia;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	@Getter @Setter
	private Usuario usuario;


}
