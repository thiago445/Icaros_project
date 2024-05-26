package com.Icaros.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Services.UserLoverService;
import com.Icaros.models.UserLover;

@RestController
@RequestMapping("/user/lover")
public class UserLoverController {
	
	@Autowired
	private UserLoverService userLoverService;
	
	
	@PostMapping
	public ResponseEntity<UserLover> create(UserLover userLover){
		this.userLoverService.create(userLover);
		   URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                .buildAndExpand(userLover.getId()).toUri();
	        return ResponseEntity.created(uri).body(userLover);
	}
	
}
