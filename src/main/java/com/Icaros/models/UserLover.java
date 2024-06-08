package com.Icaros.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario_am")
public class UserLover {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AMANTE_MUSICA")
	private Long id;
	
	@Column(name = "CPF")
	private String cpf;
	
	
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	@OneToOne
	private User user;

	public UserLover(){}
	
	//Constructor

	
	public UserLover(Long id, String cpf, User user) {
		super();
		this.id = id;
		this.cpf = cpf;
		this.user = user;
	}
	
	//Getters in Setters
	

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
		UserLover other = (UserLover) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id)
			 && Objects.equals(user, other.user);
	}
	
	
	
	
}
