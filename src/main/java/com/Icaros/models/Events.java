package com.Icaros.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_eventos")
public class Events {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EVENTO")
	private Long id;
	
	@Column(name = "LOCAL_EVENTO",nullable = false)
	private String address;
	
	@Column(name = "DATA_EVENTO",nullable = false)
	private LocalDate date;
	
	@Column(name = "HORA",nullable = false)
	private LocalTime time;
	
	@Column(name = "DESC_EVENTO")
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUTOR", referencedColumnName = "ID_PRODUTOR")
	private ProducerUser producerUser;
	
	//RELAÇÃO MUITO PARA MUITO NA TABELA EVENTOS E MUSICO
	@ManyToMany
	@JoinTable(
	    name = "tb_evento_musico",
	    joinColumns = @JoinColumn(name = "ID_EVENTO"),
	    inverseJoinColumns = @JoinColumn(name = "ID_MUSICO")
	)
	private Set<UserMusician> userMusician = new HashSet<>();
	
	//RELAÇÃO MUITOS PARA MUITOS NA TABELA USUARIOAM EVENTOS
	
	@ManyToMany
	@JoinTable(
	    name = "tb_usuarioam_eventos",
	    joinColumns = @JoinColumn(name = "ID_EVENTO"),
	    inverseJoinColumns = @JoinColumn(name = "ID_AMANTE_MUSICA")
	)
	private Set<UserLover> userLover = new HashSet<>();
	
	
	public Events() {}

	//Constructor

	public Events(Long id, String address, LocalDate date, LocalTime time, String description,
			ProducerUser producerUser) {
		super();
		this.id = id;
		this.address = address;
		this.date = date;
		this.time = time;
		this.description = description;
		this.producerUser = producerUser;
	}
	
	//Getters in Setters

	public Long getId() {
		return id;
	}

	

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ProducerUser getProducerUser() {
		return producerUser;
	}

	public void setProducerUser(ProducerUser producerUser) {
		this.producerUser = producerUser;
	}

	
	// hashCode and equals
	
	@Override
	public int hashCode() {
		return Objects.hash(address, date, description, id, producerUser, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Events other = (Events) obj;
		return Objects.equals(address, other.address) && Objects.equals(date, other.date)
				&& Objects.equals(description, other.description) && Objects.equals(id, other.id)
				&& Objects.equals(producerUser, other.producerUser) && Objects.equals(time, other.time);
	}
	
	
	
	

	
}
