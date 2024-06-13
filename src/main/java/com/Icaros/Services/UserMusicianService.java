package com.Icaros.Services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.UserMusicianRepository;
import com.Icaros.Security.UserSpringSecurity;
import com.Icaros.Services.Exceptions.AuthorizationExeption;
import com.Icaros.Services.Exceptions.ObjectNotFoundException;
import com.Icaros.models.User;
import com.Icaros.models.UserLover;
import com.Icaros.models.UserMusician;
import com.Icaros.models.UserRegistrationDTO;

import jakarta.transaction.Transactional;

@Service
public class UserMusicianService {
	
	@Autowired
	private UserMusicianRepository userMusicianRepository;
	
	@Autowired
	private UserService userService;
	
	@Transactional
	public UserMusician findById(Long id) {
		Optional<UserMusician> obj= this.userMusicianRepository.findById(id);
		return obj.orElseThrow(()-> new  RuntimeException(
				"o usuario musico nao foi encontrado"));
	}
	
	@Transactional
	public UserMusician findByEmail(String email) {
		UserMusician obj = this.userMusicianRepository.findByUserEmail(email);
		return obj;
	}
	
	public UserMusician findByUserToken() {
		UserSpringSecurity userSpringSecurity = this.userService.authenticaded();
		if (!Objects.nonNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Acesso negado!");
		}
		UserMusician obj = this.userMusicianRepository.findByUserId(userSpringSecurity.getId());

		if (Objects.isNull(obj)) {

			throw new ObjectNotFoundException("UserLover not found ");
		}

		return obj;
	}

	
	
	
	@Transactional
	public UserMusician create(UserMusician obj) {
		obj.setId(null);
		obj= this.userMusicianRepository.save(obj);
		return obj;
	}
	public UserMusician insertImage(byte[] image) {
		UserMusician userMusician = this.findByUserToken();

		userMusician.setImage(image);
		
		return userMusician = this.userMusicianRepository.save(userMusician);
	}
	
	@Transactional
	public UserMusician update(UserMusician obj) {
		UserMusician newObj = findById(obj.getId());
		newObj.setId(null);
		obj= this.userMusicianRepository.save(newObj);
		
		return obj;
		
	}
	
	@Transactional
	public void putProfile(UserRegistrationDTO userRegistrationDTO) {
		UserMusician newUserMusician = this.findByUserToken();
		if (Objects.isNull(newUserMusician)) {
			throw new ObjectNotFoundException("Not found userLover");
		}
		
		UserMusician updateUserMusician = userRegistrationDTO.getUserMusician();
		User newUser = newUserMusician.getUser();
		User updateUser = userRegistrationDTO.getUser();

		newUserMusician.setId(newUserMusician.getId());
		newUser.setId(newUserMusician.getUser().getId());
		newUser.setName(updateUser.getName());
		newUser.setMusicalGenre(updateUser.getMusicalGenre());
		newUserMusician.setComment(updateUserMusician.getComment());
		this.userMusicianRepository.save(newUserMusician);
		this.userService.update(newUser);

	}
	
	@Transactional
	public void delete(Long id) {
	findById(id);
	try {
		this.userMusicianRepository.deleteById(id);
	} catch (Exception e) {
		// TODO: handle exception
		throw new RuntimeException("Não é possivel excluir!");
	}
	}
	
	
}
