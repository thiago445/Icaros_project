package com.Icaros.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.Icaros.Security.JWTAuthenticationFilter;
import com.Icaros.Security.JWTAuthorizationFilter;
import com.Icaros.Security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS = { "/" };

	private static final String[] PUBLIC_MATCHERS_POST = { "/user/tipe", "/login" , "/user/sendToken"};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();

		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
		this.authenticationManager = authenticationManagerBuilder.build();
		http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
				.requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll().requestMatchers(PUBLIC_MATCHERS)
				.permitAll().anyRequest().authenticated().and().authenticationManager(authenticationManager)

		)

				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilter(new JWTAuthenticationFilter(this.authenticationManager, this.jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(this.authenticationManager, this.jwtUtil, this.userDetailsService));
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
