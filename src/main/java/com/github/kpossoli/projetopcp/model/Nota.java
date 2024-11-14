package com.github.kpossoli.projetopcp.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String avaliacao;

	@Getter @Setter
	private BigDecimal nota;

	@Getter @Setter
	private LocalDate data;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_aluno")
	@Getter @Setter
	private Aluno aluno;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_docente")
	@Getter @Setter
	private Docente docente;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_materia")
	@Getter @Setter
	private Materia materia;

}
