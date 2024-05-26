package com.Icaros.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.Icaros.Repository.EventsRepository;
import com.Icaros.models.Events;
import com.Icaros.models.ProducerUser;

import jakarta.transaction.Transactional;


@Repository
public class EventsService {

	@Autowired
	private EventsRepository eventsRepository;
	
	@Autowired
	private ProducerUserService produceruserService;
	
	@Transactional
	public List<Events>findAllById(Long ProducerId){
		List<Events> events = this.eventsRepository.findByProducerUser_Id(ProducerId);
		return events;
	}
	@Transactional
	public Events findById(long id) {
		Optional<Events> obj =this.eventsRepository.findById(id);
		return obj.orElseThrow(()-> new RuntimeException(
				"nao foi possivel encontrar o usuario id: "+ id));
	}
	
	@Transactional
	public Events create(Events obj) {
		
		ProducerUser producerUser = this.produceruserService.findById(obj.getProducerUser().getId());
		obj.setId(null);
		obj.setProducerUser(producerUser);
		obj= this.eventsRepository.save(obj);
		
		return obj;
	}
	@Transactional
	public void delete(Long id) {
		findById(id);
		this.eventsRepository.deleteById(id);
	}
}
