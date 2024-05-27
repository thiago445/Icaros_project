package com.Icaros.models;

import java.sql.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuario")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "ID_USUARIO")
	private Long id;
	
	@Column(name= "NOME")
	private String name;

	
	@Column(name= "EMAIL",nullable = false, unique = true)
	private String email;
	
	
	@Column(name= "SENHA", nullable = false)
	private String password;
	
	
	@Column(name= "SEXO")
    @Enumerated(EnumType.STRING)
	private Gender gender;
	
	
	@Column(name= "FLAG_TIPO_USUARIO")
	private String flagTipoUsuario;
	
	
	@Column(name= "DATA_NASC")
	 private Date birthDate;
	
	@Column(name= "TELEFONE")
	 private String telephone;
	
	@OneToOne(mappedBy= "user")
	private Login login;
	
	public enum Gender{
		F,
	   M,
	   P
	}
	
	public User() {
		
	}
	
	

	public User(Long id, String name, String email, String password, Gender gender,
			String flagTipoUsuario,String telephone, Date birthDate) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.flagTipoUsuario = flagTipoUsuario;
		this.telephone = telephone;
		this.birthDate = birthDate;
	}



	//GETTERS IN SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getFlagTipoUsuario() {
		return flagTipoUsuario;
	}

	public void setFlagTipoUsuario(String flagTipoUsuario) {
		this.flagTipoUsuario = flagTipoUsuario;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, email, flagTipoUsuario, gender, id, login, name, password, telephone);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(email, other.email)
				&& Objects.equals(flagTipoUsuario, other.flagTipoUsuario) && gender == other.gender
				&& Objects.equals(id, other.id) && Objects.equals(login, other.login)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(telephone, other.telephone);
	}
	
	
	
	
	
	

}
