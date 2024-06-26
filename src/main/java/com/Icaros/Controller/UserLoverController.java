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

import com.Icaros.Services.UserLoverService;
import com.Icaros.models.UserLover;
import com.Icaros.models.UserRegistrationDTO;

@RestController
@RequestMapping("/user/lover")
public class UserLoverController {

	@Autowired
	private UserLoverService userLoverService;

	@PostMapping
	public ResponseEntity<UserLover> create(UserLover userLover) {
		this.userLoverService.create(userLover);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userLover.getId())
				.toUri();
		return ResponseEntity.created(uri).body(userLover);
	}

	@PostMapping("/upload")
	public ResponseEntity<String> uploadUser(@RequestParam("image") MultipartFile image) {
		try {
			byte[] imageData = image.getBytes();
			this.userLoverService.insertImage(imageData);
			return new ResponseEntity<>("image saved successfully", HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<>("Error saving user", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/profileLover")
	public ResponseEntity<UserLover> findByIdToken() {
		UserLover UserLover = this.userLoverService.findByUserToken();

		return ResponseEntity.ok(UserLover);
	}

	@PutMapping
	public ResponseEntity<Void> update(@RequestBody UserRegistrationDTO userRegistrationDTO) {
		this.userLoverService.putProfile(userRegistrationDTO);
		
		return ResponseEntity.ok().body(null);
		

	}

}
