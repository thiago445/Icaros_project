package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.UserMusicianRepository;
import com.Icaros.models.UserMusician;

import jakarta.transaction.Transactional;

@Service
public class UserMusicianService {
	
	@Autowired
	private UserMusicianRepository userMusicianRepository;
	
	@Transactional
	public UserMusician findById(Long id) {
		Optional<UserMusician> obj= this.userMusicianRepository.findById(id);
		return obj.orElseThrow(()-> new  RuntimeException(
				"o usuario musico nao foi encontrado"));
	}
	
	@Transactional
	public UserMusician create(UserMusician obj) {
		obj.setId(null);
		obj= this.userMusicianRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public UserMusician update(UserMusician obj) {
		UserMusician newObj = findById(obj.getId());
		newObj.setId(null);
		obj= this.userMusicianRepository.save(newObj);
		
		return obj;
		
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
