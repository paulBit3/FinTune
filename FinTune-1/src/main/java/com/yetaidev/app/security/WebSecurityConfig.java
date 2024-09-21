package com.yetaidev.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yetaidev.filters.AuthTokenFilter;

import lombok.RequiredArgsConstructor;

/**
 * this is the security config class that configure the app middleware. we will call it
 * in authcontroller class
 */

//@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
		
//	public static final String ADMIN = "ADMIN";
//	public static final String USER = "USER";
	
	@Autowired
	private UserDetailsserviceImpl userDetailsservice;
	
	@Bean
	public AuthTokenFilter authJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsservice);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
	
	//authentication manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(auth -> auth
//			            public endpoints

						.requestMatchers("/signup").permitAll()
						.requestMatchers("/login1").permitAll()
						.requestMatchers("/stocks").permitAll()
						.requestMatchers("/stock/{symbol}").permitAll()
//			            private endpoints
						.anyRequest().authenticated())
				.authenticationProvider(authenticationProvider())
//		        Add JWT token filter
				.addFilterBefore(authJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
				.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.cors(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable)
				.build()
				;
		
	}
}
