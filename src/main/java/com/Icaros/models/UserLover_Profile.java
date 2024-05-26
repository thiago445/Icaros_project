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
@Table(name = "tb_usuarioam_portifolio")
public class UserLover_Profile {
	
	@Id
	@Column(name=  "ID_AM_PORTIFOLIO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "ID_PORTIFOLIO",referencedColumnName = "ID_PORTIFOLIO")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ID_AMANTE_MUSICA" ,referencedColumnName = "ID_AMANTE_MUSICA")
    private UserLover userLover;

	public UserLover_Profile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public UserLover getUserLover() {
		return userLover;
	}

	public void setUserLover(UserLover userLover) {
		this.userLover = userLover;
	}
    
    
    

}
