package com.Icaros.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO{


	private User user;
	
	private UserLover userLover;
	
	private UserMusician userMusician;
	
	private ProducerUser producerUser;

	

	public UserMusician getUserMusician() {
		return userMusician;
	}

	public void setUserMusician(UserMusician userMusician) {
		this.userMusician = userMusician;
	}

	public ProducerUser getProducerUser() {
		return producerUser;
	}

	public void setProducerUser(ProducerUser producerUser) {
		this.producerUser = producerUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLover getUserLover() {
		return userLover;
	}

	public void setUserLover(UserLover userLover) {
		this.userLover = userLover;
	}
	
	
}
