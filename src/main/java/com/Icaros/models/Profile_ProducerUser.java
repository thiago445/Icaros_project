package com.Icaros.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_portifolio_produtor")
public class Profile_ProducerUser {
	
	@Id
	@Column(name=  "ID_PORTIFOLIO_PRODUTOR")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	

    @ManyToOne
    @JoinColumn(name = "ID_PORTIFOLIO",referencedColumnName = "ID_PORTIFOLIO")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTOR" ,referencedColumnName = "ID_PRODUTOR")
    private ProducerUser producerUser;

	public Profile_ProducerUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public ProducerUser getProducerUser() {
		return producerUser;
	}

	public void setProducerUser(ProducerUser producerUser) {
		this.producerUser = producerUser;
	}
    
    
    

}
