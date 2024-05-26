package com.Icaros.Controller;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Services.LoginService;
import com.Icaros.models.Login;

@RestController
@RequestMapping("/user/login")

public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping
    public ResponseEntity<Login> createLogin(@RequestBody Login login) {
		
        Login createdLogin = loginService.create(login);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLogin.getId()).toUri();
        return ResponseEntity.created(uri).body(createdLogin);
        
        
    }
}	
