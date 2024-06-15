package com.Icaros.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Mail.EmailRequest;
import com.Icaros.Mail.EmailService;
import com.Icaros.Security.JWTUtil;
import com.Icaros.Services.UserService;
import com.Icaros.models.User;
import com.Icaros.models.UserRegistrationDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private EmailService emailService;

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = this.userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@GetMapping("/flagUser")
	public int getUserTipe() {
		User user = this.userService.GetUserFlag();
		return user.getFlagUserType();
	}

	@PostMapping("/tipe")
	public ResponseEntity<User> createUserWithLoginAndUserLover(
			@RequestBody @Valid UserRegistrationDTO userRegistrationDTO) {
		User createdUser = userService.createUserWithLoginAndTipeUser(userRegistrationDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId())
				.toUri();
		return ResponseEntity.created(uri).body(createdUser);
	}

	@PostMapping("/sendToken")
	public ResponseEntity<String> sendToken(@RequestBody EmailRequest emailRequest) 
	{
		String email = emailRequest.getEmail();
        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email não pode ser nulo ou vazio");
        }

        String token = jwtUtil.generateTokenEmail(email);
        String subject = "Seu token de verificação";
        String text = "Seu token de verificação é: " + token;

        emailService.sendEmail(email, subject, text);
        return ResponseEntity.ok("Token enviado para " + email);
    }
	

	@PostMapping("/verifyToken")
	public ResponseEntity<String> verifyToken(@RequestParam String email, @RequestParam String token) {
		User user = this.userService.findByUserToken();
		
		if (jwtUtil.verifyToken(user.getEmail(), token)) {
			return ResponseEntity.ok("Token verificado com sucesso");
		} else {
			return ResponseEntity.status(400).body("Token inválido");
		}
	}
}
