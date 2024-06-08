package com.Icaros.Controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Icaros.Services.ProfileService;
import com.Icaros.models.Profile;

import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/user/profile")
public class ProfileController {
	
	@Autowired
	ProfileService profileService;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Profile> findById(@PathVariable long id){
		Profile profile = this.profileService.findByIdPage(id);
		return ResponseEntity.ok(profile);
	}

	
	 @GetMapping("/musician")
	    public ResponseEntity<Profile> getPortfolioByMusicianId() {
	        Profile profile = profileService.findByMusicianId();
	        if (profile != null) {
	            return ResponseEntity.ok(profile);
	        } else {
	            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	        }
	 }
	@PostMapping
	@Validated
	public ResponseEntity<Profile> createProfile(@Valid @RequestBody Profile profile){
		this.profileService.created(profile);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/[id]").buildAndExpand(profile.getId()).toUri();
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	@Validated
	public ResponseEntity<Void> updateProfile(@Valid @RequestBody Profile profile, @PathVariable Long id){
		profile.setId(id);
		this.profileService.update(profile);
		return ResponseEntity.noContent().build();
	}
}
