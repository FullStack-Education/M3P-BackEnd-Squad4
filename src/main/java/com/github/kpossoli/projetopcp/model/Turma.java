package com.github.kpossoli.projetopcp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(of = "id")
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	@ToString.Include
	private String nomeTurma;

	@Getter @Setter
	@ToString.Include
	private LocalDate dataInicio;

	@Getter @Setter
	@ToString.Include
	private LocalDate dataTermino;

	@Getter @Setter
	@ToString.Include
	private String horario;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	@Getter @Setter
	@JsonManagedReference
	private Docente docente;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso")
	@Getter @Setter
	@JsonManagedReference
	private Curso curso;

}
