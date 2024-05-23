package com.Icaros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_musico")
public class UserMusician {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ID_MUSICO")
	private Long id;
	
	@Column(name = "CPF")
	private int cpf; //porque nao foi declarado no usuario

	
	@Column(name = "DESCRICAO")
	private String description;
	
	@OneToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private User user;
}
