package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Events_UserMusician;

@Repository
public interface Events_UserMusicianRepository extends JpaRepository<Events_UserMusician, Long>{

}
