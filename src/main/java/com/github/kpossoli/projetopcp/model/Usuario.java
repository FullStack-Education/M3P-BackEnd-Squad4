package com.github.kpossoli.projetopcp.model;

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
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String nome;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String senha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_papel")
	@Getter @Setter
	private Papel papel;

	//TODO implementar validação para não permitir cadastro de usuario com e-mail já existente

}
