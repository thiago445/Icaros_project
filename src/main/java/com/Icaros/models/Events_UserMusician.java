package com.Icaros.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_usuarioam_eventos")
public class Events_UserMusician {
	 @ManyToOne
	    @JoinColumn(name = "ID_EVENTO",referencedColumnName = "ID_EVENTO")
	    private Events events;

	    @ManyToOne
	    @JoinColumn(name = "ID_MUSICO" ,referencedColumnName = "ID_MUSICO")
	    private UserMusician userMusician;
	    
	

}