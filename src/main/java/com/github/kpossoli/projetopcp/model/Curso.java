package com.github.kpossoli.projetopcp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
	@OneToMany(mappedBy = "curso", cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
	@JsonBackReference
	private List<Turma> turmas = new ArrayList<>();

	@ManyToMany(mappedBy = "cursos")
	@Getter @Setter
	@JsonBackReference
	private List<Materia> materias = new ArrayList<>();

}
