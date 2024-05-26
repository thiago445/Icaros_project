package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long>  {

}
