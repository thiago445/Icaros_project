package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.UserLover_Profile;

@Repository	
public interface UserLover_ProfileRepository extends JpaRepository<UserLover_Profile, Long> {

}
