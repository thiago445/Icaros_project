package com.Icaros.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Services.UserService;
import com.Icaros.models.User;
import com.Icaros.models.UserRegistrationDTO;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	
	
	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj=this.userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	@Validated
	@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
	public ResponseEntity<User> created( @RequestBody User obj){

		User createdUser = userService.createUserWithLogin(obj);
		
		   URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                .buildAndExpand(createdUser.getId()).toUri();
	        return ResponseEntity.created(uri).body(createdUser);
		
	}
	
	 @PostMapping("/tipe")
	 public ResponseEntity<User> createUserWithLoginAndUserLover(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		 User createdUser = userService.createUserWithLoginAndTipeUser(userRegistrationDTO);
	     return ResponseEntity.ok(createdUser);
	 }
}	
