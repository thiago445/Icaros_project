package com.Icaros.models;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_usuario")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long id;

	@Column(name = "NOME")
	private String name;

	@NotBlank(message = "O campo nome não pode estar em branco")
	@Column(name = "EMAIL", unique = true)
	private String email;

	@NotNull
	@NotEmpty
	@Column(name = "SENHA", nullable = false)
	private String password;

	@Column(name = "SEXO")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "FLAG_TIPO_USUARIO")
	private int flagUserType;

	@Column(name = "DATA_NASC")
	private Date birthDate;

	@Column(name = "TELEFONE")
	private String telephone;
	
	@Column(name="GENERO_MUSICAL")
	@Enumerated(EnumType.STRING)
	private MusicalGenreEnum MusicalGenre;

	
	
	@ElementCollection(fetch = FetchType.EAGER)//SEMPRE BUSCAR OS PERFIS QUANDO BUSCAR O USUARIO
	@JsonProperty(access = Access.WRITE_ONLY)
	@CollectionTable(name="tb_user_profile")
	@Column(name="profile", nullable = false)
	private Set<Integer>profiles = new HashSet<>();
	
	public Set<FlagUserTypeEnum> getProfiles(){
		return this.profiles.stream().map(x -> FlagUserTypeEnum.toEnum(x)).collect(Collectors.toSet());
	}
	
	public void addProfile(FlagUserTypeEnum flagUserTypeEnum) {
		this.profiles.add(flagUserTypeEnum.getCode());
	}
	
	
	public enum Gender {
		F, M, P
	}

	public User() {

	}

	
	public User(Long id, String name,String email,String password, Gender gender, int flagUserType, Date birthDate, String telephone,
			MusicalGenreEnum musicalGenre, Set<Integer> profiles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.flagUserType = flagUserType;
		this.birthDate = birthDate;
		this.telephone = telephone;
		MusicalGenre = musicalGenre;
		this.profiles = profiles;
	}

	// GETTERS IN SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTelephone() {
		return telephone;
	}

	public Set<Integer> getFlagUserTypeEnum() {
		return profiles;
	}

	public void setFlagUserTypeEnum(Set<Integer> flagUserTypeEnum) {
		profiles = flagUserTypeEnum;
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
	
	

	public MusicalGenreEnum getMusicalGenre() {
		return MusicalGenre;
	}

	public void setMusicalGenre(MusicalGenreEnum musicalGenre) {
		MusicalGenre = musicalGenre;
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

	public int getFlagUserType() {
		return flagUserType;
	}

	public void setFlagUserType(int flagUserType) {
		this.flagUserType = flagUserType;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}



	@Override
	public int hashCode() {
		return Objects.hash(birthDate, email, flagUserType, gender, id, name, password, telephone);
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
				&& Objects.equals(flagUserType, other.flagUserType) && gender == other.gender

				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(telephone, other.telephone);
	}

}
