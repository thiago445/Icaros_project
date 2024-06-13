package com.Icaros.Services;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Icaros.Repository.ProducerUserRepository;
import com.Icaros.Repository.UserLoverRepository;
import com.Icaros.Repository.UserMusicianRepository;
import com.Icaros.Repository.UserRepository;
import com.Icaros.Security.UserSpringSecurity;
import com.Icaros.Services.Exceptions.AuthorizationExeption;
import com.Icaros.Services.Exceptions.DataBindingViolationException;
import com.Icaros.Services.Exceptions.ObjectNotFoundException;
import com.Icaros.models.FlagUserTypeEnum;
import com.Icaros.models.ProducerUser;
import com.Icaros.models.User;
import com.Icaros.models.UserLover;
import com.Icaros.models.UserMusician;
import com.Icaros.models.UserRegistrationDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserLoverRepository userLoverRepository;

	@Autowired
	private UserMusicianRepository userMusicianRepository;

	@Autowired
	private ProducerUserRepository producerUserRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public User findById(Long id) {
		UserSpringSecurity userSpringSecurity = authenticaded();
		if (!Objects.nonNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Acesso negado!");
		}
		Optional<User> obj = this.userRepository.findById(id);
		return obj.orElseThrow(
				() -> new ObjectNotFoundException("User not found! id: " + id + ",type: " + User.class.getName()));

	}
	
	public User findByUserToken() {
		UserSpringSecurity userSpringSecurity = this.authenticaded();
		if (!Objects.nonNull(userSpringSecurity)) {
			throw new AuthorizationExeption("Acesso negado!");
		}
		User obj = this.findById(userSpringSecurity.getId());

		if (Objects.isNull(obj)) {

			throw new ObjectNotFoundException("UserLover not found ");
		}

		return obj;
	}
	
	

	@Transactional
	public User GetUserFlag() {
		UserSpringSecurity userSpringSecurity = authenticaded();
		User user = findById(userSpringSecurity.getId());
		return user;
	}

	@Transactional
	public User createUserWithLoginAndTipeUser(UserRegistrationDTO userRegistrationDTO) {
		// Cria o usuário
		User user = userRegistrationDTO.getUser();
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.getFlagUserTypeEnum().add(user.getFlagUserType());

		User createdUser = userRepository.save(user);

		// Cria o login
		// Login login = userRegistrationDTO.getLogin();
		// login.setUser(createdUser);
		// loginService.create(login);

		if (createdUser.getFlagUserTypeEnum().contains(1)) {
			UserMusician userMusician = userRegistrationDTO.getUserMusician();
			createdUser.setFlagUserTypeEnum(
					Stream.of(FlagUserTypeEnum.USER_MUSICIAN.getCode()).collect(Collectors.toSet()));
			userMusician.setUser(createdUser);
			userMusicianRepository.save(userMusician);
		} else if (createdUser.getFlagUserTypeEnum().contains(2)) {
			UserLover userLover = userRegistrationDTO.getUserLover();
			userLover.setUser(createdUser);
			createdUser
					.setFlagUserTypeEnum(Stream.of(FlagUserTypeEnum.USER_LOVER.getCode()).collect(Collectors.toSet()));
			userLoverRepository.save(userLover);
		} else if (createdUser.getFlagUserTypeEnum().contains(3)) {
			ProducerUser producerUser = userRegistrationDTO.getProducerUser();
			createdUser.setFlagUserTypeEnum(
					Stream.of(FlagUserTypeEnum.USER_PRODUCER.getCode()).collect(Collectors.toSet()));
			producerUser.setUser(createdUser);
			producerUserRepository.save(producerUser);
		}
		return createdUser;

	}

	@Transactional
	public User updatePassword(User obj) {
		// como adiciconar foto de perfil
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}
	
	@Transactional
	public void update(User obj) {
		User newObj = findById(obj.getId());
		newObj.setName(obj.getName());
		newObj.setMusicalGenre(obj.getMusicalGenre());
		this.userRepository.save(newObj);
	}


	@Transactional
	public void delete(Long id) {
		findById(id);
		try {
			this.userRepository.deleteById(id);

		} catch (Exception e) {
			throw new DataBindingViolationException("Não é possivel excluir, pois a entidades relacionadas!");
		}

	}

	public static UserSpringSecurity authenticaded() {
		try {
			return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}

	// o que ele pode alterar obj.getEmail(), obj.getPassword(), obj.getGender()

}
