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
@Table(name ="tb_usuarioam_eventos")
public class Events_UserLover {
	
	@Id
	@Column(name=  "ID_AM_EVENTOS")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	    @JoinColumn(name = "ID_EVENTO",referencedColumnName = "ID_EVENTO")
	    private Events events;

	    @ManyToOne
	    @JoinColumn(name = "ID_AMANTE_MUSICA" ,referencedColumnName = "ID_AMANTE_MUSICA")
	    private UserLover userLover;

		public Events_UserLover() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Events getEvents() {
			return events;
		}

		public void setEvents(Events events) {
			this.events = events;
		}

		public UserLover getUserLover() {
			return userLover;
		}

		public void setUserLover(UserLover userLover) {
			this.userLover = userLover;
		}
	    
	    
	    
}
