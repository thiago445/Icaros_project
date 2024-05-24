package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Icaros.Repository.UserRepository;
import com.Icaros.models.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User findById(Long id) {
		
		Optional<User> obj= this.userRepository.findById(id);
		return obj.orElseThrow(() ->new RuntimeException(
				"Usuario não encontrado! id: "+ id + ",tipo: "+ User.class.getName()));
		
	}
	
	@Transactional
	private User created(User obj) {
		obj.setId(null);
		obj = this.userRepository.save(obj);
		return obj;
	}
	
	@Transactional
	private User updatePassword(User obj){
		//como adiciconar foto de perfil
		User newObj = findById(obj.getId());
		newObj.setPassword(obj.getPassword());
		return this.userRepository.save(newObj);
	}
	
	@Transactional
	private void delete(Long id){
		findById(id);
		try {
			this.userRepository.deleteById(id);
			
			
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir!");
		}
	
	}
	
	
	//o que ele pode alterar obj.getEmail(), obj.getPassword(), obj.getGender()
	

}
