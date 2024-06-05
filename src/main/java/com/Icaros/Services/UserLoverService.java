package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.UserLoverRepository;
import com.Icaros.Services.Exceptions.ObjectNotFoundException;
import com.Icaros.models.UserLover;

import jakarta.transaction.Transactional;

@Service
public class UserLoverService {

	@Autowired
	private UserLoverRepository userLoverRepository;

	@Transactional
	public UserLover findById(Long id) {
		Optional<UserLover> obj = this.userLoverRepository.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"usuario nao encontrado"));
	}
	@Transactional
	public UserLover create(UserLover obj) {
		
		obj.setId(null);	
		obj= this.userLoverRepository.save(obj);
		return obj;
	}
	
	@Transactional
	public  void delete(Long id) {
		findById(id);
		this.userLoverRepository.deleteById(id);
	}
}
