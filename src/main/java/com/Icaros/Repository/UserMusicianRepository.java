package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.UserMusician;

@Repository
public interface UserMusicianRepository extends JpaRepository<UserMusician,Long>  {
	UserMusician findByUserEmail(String email);
	UserMusician findByUserId(Long userId);

}
