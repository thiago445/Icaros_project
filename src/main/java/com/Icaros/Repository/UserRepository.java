package com.Icaros.Repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Icaros.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);
	User findByflagUserType(int flagUserType);
	
}
