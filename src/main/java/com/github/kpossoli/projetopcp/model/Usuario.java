package com.github.kpossoli.projetopcp.model;

import jakarta.persistence.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@EqualsAndHashCode(of = "id")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String nome;

	@Column(unique = true)
	@Getter @Setter
	private String email;

	@Getter @Setter
	private String senha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_papel")
	@Getter @Setter
	private Papel papel;

}
