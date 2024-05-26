package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Icaros.Repository.ProducerUserRepository;
import com.Icaros.Repository.UserLoverRepository;
import com.Icaros.Repository.UserMusicianRepository;
import com.Icaros.Repository.UserRepository;
import com.Icaros.models.Login;
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
	private LoginService loginService;
	
	@Autowired
	private UserLoverRepository userLoverRepository;
	
	@Autowired
	private UserMusicianRepository userMusicianRepository;
	
	@Autowired
	private ProducerUserRepository producerUserRepository;
	
	public User findById(Long id) {
		
		Optional<User> obj= this.userRepository.findById(id);
		return obj.orElseThrow(() ->new RuntimeException(
				"Usuario não encontrado! id: "+ id + ",tipo: "+ User.class.getName()));
		
	}
	
	
	@Transactional
	public User create(User obj) {
		obj.setId(null);
		obj = this.userRepository.save(obj);
		return obj;
	}
	
	public User createUserWithLogin(User user) {
        // Cria o usuário
        User createdUser = create(user);

        // Cria o login
        Login login = new Login();
        login.setEmail(user.getEmail());
        login.setPassword(user.getPassword());
        login.setUser(createdUser);
        loginService.create(login);

        
        return createdUser;
    }
	
	
	@Transactional
    public User createUserWithLoginAndTipeUser(UserRegistrationDTO userRegistrationDTO) {
        // Cria o usuário
        User user = userRegistrationDTO.getUser();
        User createdUser = userRepository.save(user);

        // Cria o login
        //Login login = userRegistrationDTO.getLogin();
        //login.setUser(createdUser);
        //loginService.create(login);
        
        Login login = new Login();
        login.setEmail(createdUser.getEmail());
        login.setPassword(createdUser.getPassword());
        login.setUser(createdUser);
        loginService.create(login);
        
        if (createdUser.getFlagTipoUsuario().equals("AM")){

        // Cria o UserLover
        UserLover userLover = userRegistrationDTO.getUserLover();
        userLover.setUser(createdUser);
        userLoverRepository.save(userLover);
        }
        else if (createdUser.getFlagTipoUsuario().equals("produtor")) {
        	 ProducerUser producerUser = userRegistrationDTO.getProducerUser();
             producerUser.setUser(createdUser);
             producerUserRepository.save(producerUser);
        }
        else if (createdUser.getFlagTipoUsuario().equals("musico")) {
        	UserMusician userMusician = userRegistrationDTO.getUserMusician();
        	userMusician.setUser(createdUser);
        	userMusicianRepository.save(userMusician);
        }
        return createdUser;
    }
	
	
	
	@Transactional
	public User updatePassword(User obj){
		//como adiciconar foto de perfil
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}
	
	@Transactional
	public void delete(Long id){
		findById(id);
		try {
			this.userRepository.deleteById(id);
			
			
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir!");
		}
	
	}
	
	
	//o que ele pode alterar obj.getEmail(), obj.getPassword(), obj.getGender()
	

}
