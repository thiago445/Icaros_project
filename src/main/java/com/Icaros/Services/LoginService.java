package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.LoginRepository;
import com.Icaros.Repository.UserRepository;
import com.Icaros.models.Login;
import com.Icaros.models.User;

import jakarta.transaction.Transactional;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public Login findById(Long id) {
		Optional<Login> obj = this.loginRepository.findById(id);
		return obj.orElseThrow(()-> new RuntimeException(
				"usuario nao encontrado id: " +id));
	}
	
	@Transactional
	public Login create(Login obj){
		User user = userRepository.findByEmail(obj.getEmail());
		try {
			obj.setUser(user);	
		} catch (Exception e) {
			// TODO: handle exception
			 throw new RuntimeException("User not found");
		}
		
		obj.setId(null);
		obj.setEmail(user.getEmail());
		obj.setPassword(user.getPassword());
		obj= this.loginRepository.save(obj);
	    return obj;
		
	}
	
	
	@Transactional
	public Login update(Login obj) {
	Login newObj= findById(obj.getId());
	newObj.setPassword(obj.getPassword());
	return this.loginRepository.save(newObj);
	}
	
	@Transactional
	public Login updatePassword(Long id, String Password) {
		Login obj = findById(id);
		obj.setPassword(Password);
		return this.loginRepository.save(obj);
	}
	
	public void delete(Long id) {
		findById(id);
		try {
			this.loginRepository.deleteById(id);
			
			
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir!");
		}
	
		
	}
}
