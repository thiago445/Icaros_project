package com.Icaros.Services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.ProfileRepository;
import com.Icaros.Security.UserSpringSecurity;
import com.Icaros.Services.Exceptions.AuthorizationExeption;
import com.Icaros.Services.Exceptions.ObjectNotFoundException;
import com.Icaros.models.Profile;
import com.Icaros.models.UserMusician;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private UserMusicianService userMusicianService;

	@Transactional
	public Profile findById(Long id) {
		UserSpringSecurity userSpringSecurity = UserService.authenticaded();
		Optional<Profile> obj = this.profileRepository.findById(id);
		if (Objects.isNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Access denied");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Profile not found"));

	}

	@Transactional
	public Profile findByIdVeryfication(Long id, UserSpringSecurity userSpringSecurity) {
		Optional<Profile> obj = this.profileRepository.findById(id);
		 Profile profile = obj.get();
		if (Objects.isNull(userSpringSecurity) || !userSpringSecurity.getUsername().equals(profile.getUserMusician().getUser().getEmail())) {
			throw new AuthorizationExeption("Access denied");
		}
		return obj.orElseThrow(() -> new ObjectNotFoundException("Profile not found"));

	}

	@Transactional
	public Profile findByIdPage(Long id) {
		UserSpringSecurity userSpringSecurity = UserService.authenticaded();
		Profile profile = this.findByIdVeryfication(id, userSpringSecurity);
		
		
		return profile;

	}

	@Transactional
	public Profile created(Profile obj) {
		UserSpringSecurity userSpringSecurity = UserService.authenticaded();
		if (Objects.isNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Acesso negado");
		}

		UserMusician userMusician = this.userMusicianService.findByEmail(userSpringSecurity.getUsername());

		if ((Objects.isNull(userMusician)
				|| !userSpringSecurity.getUsername().equals(userMusician.getUser().getEmail()))) {

			throw new AuthorizationExeption("User type is not a musician");

		}

		obj.setUserMusician(userMusician);
		obj = this.profileRepository.save(obj);
		return obj;
	}

	@Transactional
	public Profile update(Profile obj) {
		Profile newObj = findById(obj.getId());
		newObj.setId(null);
		this.profileRepository.save(newObj);
		return newObj;

	}

	@Transactional
	public void delete(Long id) {
		findById(id);
		this.profileRepository.deleteById(id);

	}

}
