package com.Icaros.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login,Long>  {

	
}
