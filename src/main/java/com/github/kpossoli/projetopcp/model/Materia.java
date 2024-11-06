package com.github.kpossoli.projetopcp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
public class Materia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	private String nome;

	@ManyToMany(mappedBy = "materias")
	@Getter @Setter
	@JsonBackReference
	private List<Docente> docentes = new ArrayList<>();

	@Getter @Setter
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	@JsonManagedReference
	@JoinTable(
		name = "curso_materia",
		joinColumns = @JoinColumn(name = "materia_id"),
		inverseJoinColumns = @JoinColumn(name = "curso_id")
	)
	private List<Curso> cursos = new ArrayList<>();

}
