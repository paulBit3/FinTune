package com.yetaidev.app.security;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yetaidev.model.Users;

import lombok.Data;

/*
 * this class defines the custom UserDetailsService which loads a user’s data given its username -
 */

@Data
public class CustomUserDetails implements UserDetails {
	private Long id;
	private String username;
	private String email;
	private String password;
	private String full_name;
	private String phone;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	//constructor
	public CustomUserDetails(Long id, String username, String email, String password, String full_name, String phone,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.full_name = full_name;
		this.phone = phone;
		
		
		
		this.authorities = authorities;
	}
	
	public static CustomUserDetails build(Users user) {
		//List<SimpleGrantedAuthority> auths = Collections.singletonList(new SimpleGrantedAuthority());
		Set<GrantedAuthority> authorities = user.getRoles().stream()
				.map((role) -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toSet());
		
		return new CustomUserDetails(
				user.getId(),
				user.getUsername(),
				user.getEmail(),
				user.getPassword(),
				user.getFull_name(),
				user.getPhone(),
				authorities
				);
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
