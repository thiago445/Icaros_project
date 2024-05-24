package com.Icaros.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_portifolio_produtor")
public class Profile_ProducerUser {
	

    @ManyToOne
    @JoinColumn(name = "ID_PORTIFOLIO",referencedColumnName = "ID_PORTIFOLIO")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "ID_PRODUTOR" ,referencedColumnName = "ID_PRODUTOR")
    private ProducerUser producerUser;
    

}
