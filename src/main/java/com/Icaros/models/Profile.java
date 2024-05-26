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

	//Constructor
	public Profile() {	}


	//getters in setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public UserMusician getUserMusician() {
		return userMusician;
	}


	public void setUserMusician(UserMusician userMusician) {
		this.userMusician = userMusician;
	}

	// hashCode and equals
	@Override
	public int hashCode() {
		return Objects.hash(comment, description, id, link, userMusician);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(link, other.link)
				&& Objects.equals(userMusician, other.userMusician);
	}
	
	
	
	
	
	
}
