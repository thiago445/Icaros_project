package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.ProfileRepository;
import com.Icaros.models.Profile;

import jakarta.transaction.Transactional;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Transactional
	public Profile findById(Long id) {
		Optional<Profile> obj= this.profileRepository.findById(id);
		return obj.orElseThrow(() -> new RuntimeException(
				"Usuario nao encontrado"));
		
	}
	
	@Transactional
	public Profile created(Profile obj) {
		obj.setId(null);
		obj= this.profileRepository.save(obj);
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
