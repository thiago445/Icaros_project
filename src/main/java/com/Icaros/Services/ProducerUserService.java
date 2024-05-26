package com.Icaros.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Icaros.Repository.ProducerUserRepository;
import com.Icaros.models.ProducerUser;

import jakarta.transaction.Transactional;

@Service
public class ProducerUserService {
	
	@Autowired 
	private ProducerUserRepository producerUserRepository;
	
	@Transactional
	public ProducerUser findById(Long id) {
		Optional<ProducerUser> obj = this.producerUserRepository.findById(id);
		return obj.orElseThrow(()-> new RuntimeException(
				"Produtor nao encontrado") );
	}
	
	@Transactional
	public ProducerUser created(ProducerUser obj) {
		obj.setId(null);
		obj=this.producerUserRepository.save(obj);
		return obj;
	}
	
	public ProducerUser update(ProducerUser obj) {
		ProducerUser newObj= this.findById(obj.getId());
		newObj.setFantasynName(obj.getFantasynName());
		return newObj;
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			this.producerUserRepository.deleteById(id);
			
		} catch (Exception e) {
			throw new RuntimeException("Não é possivel excluir pois há Eventos em seu nome!");
		}
	}
	
}
