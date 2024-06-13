package com.Icaros.Services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.UserLoverRepository;
import com.Icaros.Security.UserSpringSecurity;
import com.Icaros.Services.Exceptions.AuthorizationExeption;
import com.Icaros.Services.Exceptions.ObjectNotFoundException;
import com.Icaros.models.User;
import com.Icaros.models.UserLover;
import com.Icaros.models.UserRegistrationDTO;

import jakarta.transaction.Transactional;

@Service
public class UserLoverService {

	@Autowired
	private UserLoverRepository userLoverRepository;

	@Autowired
	private UserService userService;

	@Transactional
	public UserLover findById(Long id) {
		Optional<UserLover> obj = this.userLoverRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("usuario nao encontrado"));
	}

	public UserLover findByUserToken() {
		UserSpringSecurity userSpringSecurity = this.userService.authenticaded();
		if (!Objects.nonNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Acesso negado!");
		}
		UserLover obj = this.userLoverRepository.findByUserId(userSpringSecurity.getId());

		if (Objects.isNull(obj)) {

			throw new ObjectNotFoundException("UserLover not found ");
		}

		return obj;
	}

	@Transactional
	public UserLover create(UserLover obj) {

		obj.setId(null);
		obj = this.userLoverRepository.save(obj);
		return obj;
	}

	public UserLover insertImage(byte[] image) {
		UserLover userLover = this.findByUserToken();

		userLover.setImage(image);
		return userLover = this.userLoverRepository.save(userLover);
	}

	@Transactional
	public void putProfile(UserRegistrationDTO userRegistrationDTO) {
		UserLover newUserLover = this.findByUserToken();
		if (Objects.isNull(newUserLover)) {
			throw new ObjectNotFoundException("Not found userLover");
		}
		
		UserLover updateUserLover = userRegistrationDTO.getUserLover();
		User newUser = newUserLover.getUser();
		User updateUser = userRegistrationDTO.getUser();

		newUserLover.setId(newUserLover.getId());
		newUser.setId(newUserLover.getUser().getId());
		newUser.setName(updateUser.getName());
		newUser.setMusicalGenre(updateUser.getMusicalGenre());
		newUserLover.setComment(updateUserLover.getComment());
		this.userLoverRepository.save(newUserLover);
		this.userService.update(newUser);

	}

	@Transactional
	public void delete(Long id) {
		findById(id);
		this.userLoverRepository.deleteById(id);
	}
}
