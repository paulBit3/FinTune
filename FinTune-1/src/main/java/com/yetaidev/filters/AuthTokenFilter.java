package com.yetaidev.filters;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.yetaidev.app.security.AuthTokenProvider;
import com.yetaidev.app.security.UserDetailsserviceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * This class will filter any request sent by the user.
 * When a client sends a request to the server, it must pass through filters.
 * So it make sure that validate the token being accompanied in the HTTP request.
 * The JwtFilter extends the already built in OncePerRequestFilter to make sure that this filter is 
 * invoked every time a request is made to the server
 */

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private UserDetailsserviceImpl userDetailsservice;
	
	@Autowired
	private AuthTokenProvider authTokenProvider;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			retrieveJwtFromRequest(request)
				.flatMap(authTokenProvider::validateTokenAndGetJws)
				.ifPresent(jws -> {
					String email = jws.getBody().getSubject();
					UserDetails userDetails = userDetailsservice.loadUserByUsername(email);
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
		    				userDetails, null, userDetails.getAuthorities() //return authority grant to the user
		    		);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				});
		} catch (Exception e) {
			logger.error("User authentication set failed:{}", e);
		}
		
		filterChain.doFilter(request, response);
	}

	private Optional<String> retrieveJwtFromRequest(HttpServletRequest request) {
		String authHeader = request.getHeader(TOKEN_HEADER);
		if (StringUtils.hasText(authHeader) && authHeader.startsWith(TOKEN_PREFIX)) {
			return Optional.of(authHeader.replace(TOKEN_PREFIX, ""));
		}
		return Optional.empty();
	}
	
}
