package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Profile_ProducerUser;

@Repository
public interface Profile_ProducerUserRepository extends JpaRepository<Profile_ProducerUser, Long> {

}
