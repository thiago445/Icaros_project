package com.Icaros.Controller;

import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Services.UserMusicianService;
import com.Icaros.models.UserLover;
import com.Icaros.models.UserMusician;
import com.Icaros.models.UserRegistrationDTO;

@RestController
@RequestMapping("/user/musician")
public class UserMusicianController {

	@Autowired
	private UserMusicianService userMusicianService;

	@PostMapping
	public ResponseEntity<UserMusician> create(UserMusician userMusician) {
		this.userMusicianService.create(userMusician);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userMusician.getId())
				.toUri();
		return ResponseEntity.created(uri).body(userMusician);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadUser(@RequestParam("image") MultipartFile image) {
		try {
			byte[] imageData = image.getBytes();
			this.userMusicianService.insertImage(imageData);
			return new ResponseEntity<>("image saved successfully", HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>("Error saving user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/profileMusician")
	public ResponseEntity<UserMusician> findByIdToken() {
		UserMusician userMusician = this.userMusicianService.findByUserToken();

		return ResponseEntity.ok(userMusician);
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		this.userMusicianService.putProfile(userRegistrationDTO);
		
		return ResponseEntity.ok().body(null);
		

	}

}
