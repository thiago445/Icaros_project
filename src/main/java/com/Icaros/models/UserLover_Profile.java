package com.Icaros.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuarioam_portifolio")
public class UserLover_Profile {
	
    @ManyToOne
    @JoinColumn(name = "ID_PORTIFOLIO",referencedColumnName = "ID_PORTIFOLIO")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ID_AMANTE_MUSICA" ,referencedColumnName = "ID_AMANTE_MUSICA")
    private UserLover userLover;
    

}
