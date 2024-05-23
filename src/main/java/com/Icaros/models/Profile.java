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
@Table(name = "tb_portifolio")
public class Profile {
	
	@Id
	@Column(name = "ID_PORTIFOLIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESC_PORTIFOLIO")
	private String description;
	
	@Column(name = "LINK")
	private String link;
	
	@Column(name = "COMENTARIO")
	private String comment;
	
	@OneToOne
	@JoinColumn(name = "ID_MUSICO", referencedColumnName = "ID_MUSICO") //A LIGAÇÃO É COM O ID MUSICO
	private UserMusician userMusician;
	
}
