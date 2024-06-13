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
@Table(name = "tb_usuario_produtor")
public class ProducerUser {

	@Id
	@Column(name = "ID_PRODUTOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "CNPJ", unique = true)
	private String cnpj;

	@Column(name = "NOME_FANTASIA")
	private String fantasyName;

	@Column(name = "COMENTARIO")
	private String comment;

	@Lob
	@Column(name = "IMAGEM")
	private byte[] image;

	@OneToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private User user;

	public ProducerUser() {
	}

	// Constructor
	public ProducerUser(Long id, String cnpj, String fantasyName, String comment, byte[] image, User user) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.fantasyName = fantasyName;
		this.comment = comment;
		this.image = image;
		this.user = user;
	}

	// getters in setters

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public void setFantasyName(String fantasyName) {
		this.fantasyName = fantasyName;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getFantasyName() {
		return fantasyName;
	}

	public void setFantasynName(String fantasyName) {
		this.fantasyName = fantasyName;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	// hashCode and equals

	@Override
	public int hashCode() {
		return Objects.hash(cnpj, fantasyName, id, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProducerUser other = (ProducerUser) obj;
		return Objects.equals(cnpj, other.cnpj) && Objects.equals(fantasyName, other.fantasyName)
				&& Objects.equals(id, other.id) && Objects.equals(user, other.user);
	}

}
