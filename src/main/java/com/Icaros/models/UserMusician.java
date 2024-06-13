package com.Icaros.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_musico")
public class UserMusician {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_MUSICO")
	private Long id;

	@Column(name = "CPF", unique = true)
	private String cpf;

	@Lob
	@Column(name = "IMAGEM")
	private byte[] image;

	@Column(name = "COMENTARIO")
	private String comment;

	@OneToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private User user;

	public UserMusician() {

	}

	// Constructors

	public UserMusician(Long id, String cpf, byte[] image, String comment, User user) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.image = image;
		this.comment = comment;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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
		return Objects.hash(cpf, id, user);
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
		return cpf == other.cpf && Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}

}
