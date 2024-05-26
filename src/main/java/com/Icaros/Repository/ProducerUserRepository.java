package com.Icaros.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.ProducerUser;

@Repository
public interface ProducerUserRepository extends JpaRepository<ProducerUser,Long> {

	
}
