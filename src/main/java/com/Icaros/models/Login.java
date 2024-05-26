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
@Table(name = "login")
public class Login {
	
	@Id
	@Column(name=  "ID_LOGIN")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name= "EMAIL",nullable = false, unique = true)
	private String email;
	
	@Column(name="SENHA", nullable = false)
	private String password;
	
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	@OneToOne
	private User user;
	
	
	public Login() {
		
	}
	
	//GETTERS IN SETTERS


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
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
		return Objects.hash(email, id, password, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(user, other.user);
	}
	
	

	
	
	
	
}
