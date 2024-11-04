package com.github.kpossoli.projetopcp.model;

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
	private List<Docente> docentes = new ArrayList<>();

	@ManyToMany(mappedBy = "materias")
	@Getter @Setter
	private List<Curso> cursos = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "id_docente")
	@Getter @Setter
	private Docente docente;

}
