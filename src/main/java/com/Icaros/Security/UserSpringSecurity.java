package com.Icaros.Security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Icaros.models.FlagUserTypeEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserSpringSecurity implements UserDetails  {
	
	private Long id;
	
	private String username;
	
	private String password;
	
	public Collection<? extends GrantedAuthority> authorities;
	
	
	public UserSpringSecurity(Long id, String email, String password,Set<FlagUserTypeEnum> flagUserTypeEnums) {
		this.id = id;
		this.username = email;
		this.password = password;
		this.authorities = flagUserTypeEnums.stream().map(x -> new SimpleGrantedAuthority(x.getDescription())).collect(Collectors.toList());
	}
	
	public boolean hasRole(FlagUserTypeEnum flagUserTypeEnum) {
		return getAuthorities().contains(new SimpleGrantedAuthority(flagUserTypeEnum.getDescription()));
		
	}
	

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
