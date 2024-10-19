package com.github.kpossoli.projetopcp.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

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

	@Getter @Setter
	private String nome;

	@Getter @Setter
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "id_curso")
	private List<Turma> turmas = new ArrayList<>();

	@Getter @Setter
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "id_curso")
	private List<Materia> materias = new ArrayList<>();

}
