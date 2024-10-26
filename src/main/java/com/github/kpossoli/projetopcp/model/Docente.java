package com.github.kpossoli.projetopcp.model;

import java.time.LocalDate;
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
public class Docente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter
	private Long id;

	@Getter @Setter
	private String nome;

	@Getter @Setter
	private String genero;

	@Column(name = "data_nascimento")
	@Getter @Setter
	private LocalDate dataNascimento;

	@Getter @Setter
	private String naturalidade;

	@Getter @Setter
	private String cpf;

	@Getter @Setter
	private String rg;

	@Getter @Setter
	private String telefone;

	@Getter @Setter
	private String email;

	@Getter @Setter
	private String senha;

	@Column(name = "estado_civil")
	@Getter @Setter
	private String estadoCivil;

	@Getter @Setter
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private List<Materia> materias = new ArrayList<>();

	@Getter @Setter
	private String cep;

	@Getter @Setter
	private String cidade;

	@Getter @Setter
	private String estado;

	@Getter @Setter
	private String logradouro;

	@Getter @Setter
	private Long numero;

	@Getter @Setter
	private String complemento;

	@Getter @Setter
	private String bairro;

	@Column (name = "ponto_de_referencia")
	@Getter @Setter
	private String pontoDeReferencia;

	@OneToOne
	@JoinColumn(name = "id_usuario")
	@Getter @Setter
	private Usuario usuario;

	//TODO implementar validação para não permitir cadastro de docente com e-mail já existente

}
