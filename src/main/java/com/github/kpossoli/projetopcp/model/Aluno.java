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

	@Getter @Setter
	private String nome;

	@Getter @Setter
	private String telefone;

	@Getter @Setter
	private String genero;

	@Getter @Setter
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "id_usuario")
	private List<Turma> turmas = new ArrayList<>();

	@Column(name = "data_nascimento")
	@Getter @Setter
	private LocalDate dataNascimento;

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
	private String rua;

	@Getter @Setter
	private Long numero;

	@Getter @Setter
	private String cidade;

	@Getter @Setter
	private String estado;

	@Getter @Setter
	private String complemento;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	@Getter @Setter
	private Usuario usuario;

}
