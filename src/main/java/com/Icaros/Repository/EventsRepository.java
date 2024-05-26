package com.Icaros.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Events;

@Repository
public interface EventsRepository extends JpaRepository<Events,Long>  {
	List<Events> findByProducerUser_Id(Long id);

}
