package com.Icaros.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	@Column(name = "CPF",unique = true)
	private String cpf; //porque nao foi declarado no usuario
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GENERO_MUSICAL")
	private MusicalGenre musicalGenre;

	
	@Column(name = "DESCRICAO")
	private String description;
	
	@OneToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private User user;
	
	
	public enum MusicalGenre{
		Rock,
		Sertanejo,
		Pop,
		Hip_Hop,
		Jazz,
		Blues,
		Classical,
		Electronic_Dance_Music,
		Country,
		Reggae,
		Reggaeton,
		Soul,
		Funk,
		Disco,
		Gospel,
		Todos
	}
	
	public UserMusician() {
		
	}
	
	// Getters and Setters

	public UserMusician(Long id, String cpf, MusicalGenre musicalGenre, String description, User user) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.musicalGenre = musicalGenre;
		this.description = description;
		this.user = user;
	}
	
	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public MusicalGenre getMusicalGenre() {
		return musicalGenre;
	}

	public void setMusicalGenre(MusicalGenre musicalGenre) {
		this.musicalGenre = musicalGenre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	 // hashCode and equals
		@Override
		public int hashCode() {
			return Objects.hash(cpf, description, id, musicalGenre, user);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			UserMusician other = (UserMusician) obj;
			return cpf == other.cpf && Objects.equals(description, other.description) && Objects.equals(id, other.id)
					&& musicalGenre == other.musicalGenre && Objects.equals(user, other.user);
		}
	
	
	
	
	
	
	
}

