package com.github.kpossoli.projetopcp.model;

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
public class Curso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	private String nome;

	@Getter @Setter
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_curso")
	private List<Turma> turmas = new ArrayList<>();

	@Getter @Setter
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_curso")
	private List<Materia> materias = new ArrayList<>();

}
