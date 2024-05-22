package com.Icaros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "login")
public class Login {
	
	@Id
	@Column(name=  "ID_LOGIN")
	private Long id;
	
	@Column(name= "EMAIL")
	private String email;
	
	@Column(name="SENHA")
	private int password;
	
	@Column(name = "ID_USUARIO")
	@OneToOne
	private User user;
	
	
	public Login() {
		
	}

	
	
	
	
}
