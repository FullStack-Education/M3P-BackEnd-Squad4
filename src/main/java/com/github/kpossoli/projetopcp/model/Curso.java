package com.github.kpossoli.projetopcp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_curso")
	@JsonBackReference
	private List<Turma> turmas = new ArrayList<>();

	@Getter @Setter
	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "id_curso")
	private List<Materia> materias = new ArrayList<>();

}
