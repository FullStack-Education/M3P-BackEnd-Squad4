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
public class Turma {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Column(unique = true)
	@Getter @Setter
	private String nome;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	@Getter @Setter
	private Docente docente;

	@OneToMany(fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "id_turma")
	private List<Aluno> alunos = new ArrayList<>();

}
