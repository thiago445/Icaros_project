package com.Icaros.Services;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.Icaros.Repository.UserRepository;
import com.Icaros.Security.UserSpringSecurity;
import com.Icaros.models.User;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = this.userRepository.findByEmail(email);
		


		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException("User not Found: " + email);
		}

		return new UserSpringSecurity(user.getId(), user.getEmail(), user.getPassword(),user.getMusicalGenre(),user.getFlagUserType(), user.getProfiles());
		
	}

}
