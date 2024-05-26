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
@Table(name = "tb_evento_musico")
public class Events_UserMusician {
	
	@Id
	@Column(name=  "ID_EVENTO_MUSICO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	    @JoinColumn(name = "ID_EVENTO",referencedColumnName = "ID_EVENTO")
	    private Events events;

	    @ManyToOne
	    @JoinColumn(name = "ID_MUSICO" ,referencedColumnName = "ID_MUSICO")
	    private UserMusician userMusician;

		public Events_UserMusician() {
		
			// TODO Auto-generated constructor stub
		}

		public Events getEvents() {
			return events;
		}

		public void setEvents(Events events) {
			this.events = events;
		}

		public UserMusician getUserMusician() {
			return userMusician;
		}

		public void setUserMusician(UserMusician userMusician) {
			this.userMusician = userMusician;
		}
	    
	
	    

}
