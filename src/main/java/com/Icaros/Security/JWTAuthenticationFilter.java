package com.Icaros.Security;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.Icaros.exceptions.GlobalExceptionHandler;
import com.Icaros.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//CAI TUDO AQUI NO LOGIN
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private AuthenticationManager authenticationManager;
	
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		super();
		setAuthenticationFailureHandler(new GlobalExceptionHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			User userCreadentials = new ObjectMapper().readValue(request.getInputStream(), User.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					userCreadentials.getEmail(), userCreadentials.getPassword(), new ArrayList<>());
			
			
			Authentication authentication = this.authenticationManager.authenticate(authToken);
			return authentication;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void successfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain, Authentication authentication) throws IOException, ServerException{
			UserSpringSecurity userSpringSecurity = (UserSpringSecurity) authentication.getPrincipal();
			String email =userSpringSecurity.getUsername();
			String token= this.jwtUtil.genereateToken(email);
			response.addHeader("Authorization", "Bearer "+token);
			response.addHeader("access-control-expose-headers","Authorization");
		}
	
	
	
	
	
	
	
	
	
	
}
