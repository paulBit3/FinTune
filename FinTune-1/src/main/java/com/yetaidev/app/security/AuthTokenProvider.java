package com.yetaidev.app.security;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;

/*
 * This class will filter any request sent by the user.
 * When a client sends a request to the server, it must pass through filters.
 * So it make sure that validate the token being accompanied in the HTTP request.
 * The JwtFilter extends the already built in OncePerRequestFilter to make sure that this filter is 
 * invoked every time a request is made to the server
 */

@Slf4j
@Component
public class AuthTokenProvider {
	//Jwt token secret key
	@Value("${jwt.token.secret.key}")
	private String jwtKey;
	
	//Jwt token secret key expiration
	@Value("${jwt.token.expirationTime}")
	private Long tokenExpiration;
	
	 public static final String TOKEN_TYPE = "JWT";
	 public static final String TOKEN_ISSUER = "fintune-api";
	    public static final String TOKEN_AUDIENCE = "fintune-app";
	
	public String generateToken(Authentication auth) {
		CustomUserDetails user = (CustomUserDetails) auth.getPrincipal();
		
		List<String> roles = user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		
		//create a array byte of keys
		byte[] loginKey = jwtKey.getBytes();
		
		return Jwts.builder()
				.setHeaderParam("type", TOKEN_TYPE)
                .signWith(Keys.hmacShaKeyFor(loginKey), SignatureAlgorithm.HS512)
                .setExpiration(Date.from(ZonedDateTime.now().plusHours(tokenExpiration).toInstant()))
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setId(UUID.randomUUID().toString())
                .setIssuer(TOKEN_ISSUER)
                .setAudience(TOKEN_AUDIENCE)
                .setSubject(user.getEmail())
                .claim("authority", roles)
                .compact();
                //.claim("full_name", user.getFull_name())
                //.claim("preferred_username", user.getUsername())
                //.claim("email", user.getEmail())
                
	}
	
	public Optional<Jws<Claims>> validateTokenAndGetJws(String token) {
		try {
			byte[] loginKey = jwtKey.getBytes();
			
			return (Optional<Jws<Claims>>) Jwts
					.parser()
					.setSigningKey(Keys.hmacShaKeyFor(loginKey))
					.parseClaimsJws(token)
					.getBody()
					.get(loginKey);
			
		
		} catch(ExpiredJwtException exception) {
			log.error("JWT request expired : {} failed : {}", token, exception.getMessage());
		} catch(UnsupportedJwtException exception) {
			log.error("JWT request unsupported : {} failed : {}", token, exception.getMessage());
		} catch(MalformedJwtException exception) {
			log.error("JWT request invalid : {} failed : {}", token, exception.getMessage());
		} catch(SignatureException exception) {
			log.error("JWT request invalid signature : {} failed : {}", token, exception.getMessage());
		} catch(IllegalArgumentException exception) {
			log.error("JWT request return empty or null : {} failed : {}", token, exception.getMessage());
		}
		return null;
	}
}
