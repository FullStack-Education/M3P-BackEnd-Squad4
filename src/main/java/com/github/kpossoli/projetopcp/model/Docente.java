package com.github.kpossoli.projetopcp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
public class Docente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String nomeCompleto;

	@Getter @Setter
	private String genero;

	@Column(name = "data_nascimento")
	@Getter @Setter
	private LocalDate nascimento;

	@Getter @Setter
	private String naturalidade;

	@Getter @Setter
	private String cpf;

	@Getter @Setter
	private String rg;

	@Getter @Setter
	private String telefone;

	@Column(unique = true)
	@Getter @Setter
	private String email;

	@Getter @Setter
	private String senha;

	@Column(name = "estado_civil")
	@Getter @Setter
	private String estadoCivil;

	@Getter @Setter
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinTable(
		name = "docente_materia",
		joinColumns = @JoinColumn(name = "docente_id"),
		inverseJoinColumns = @JoinColumn(name = "materia_id")
	)
	private List<Materia> materias = new ArrayList<>();

	@Getter @Setter
	private String cep;

	@Getter @Setter
	private String localidade;

	@Getter @Setter
	private String uf;

	@Getter @Setter
	private String logradouro;

	@Getter @Setter
	private String numero;

	@Getter @Setter
	private String complemento;

	@Getter @Setter
	private String bairro;

	@Column (name = "ponto_de_referencia")
	@Getter @Setter
	private String referencia;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_usuario")
	@Getter @Setter
	private Usuario usuario;

}
