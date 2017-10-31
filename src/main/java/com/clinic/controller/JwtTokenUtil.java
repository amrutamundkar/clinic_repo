package com.clinic.controller;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.clinic.exception.ClinicException;
import com.clinic.model.JwtUser;
import com.clinic.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenUtil implements Serializable {	
	
	

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private ObjectMapper objectMapper = new ObjectMapper();

	private static final long serialVersionUID = -3301605591108950415L;

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_ROLES = "roles";
	static final String CLAIM_KEY_CREATED = "created";
	static final String CLAIM_USER_DETAILS = "userDetails";

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	public User getUserDetails(String token) throws ClinicException {
		User userDetails = null;
		try {
			final Claims claims = getClaimsFromToken(token);
			Object obj = claims.get(CLAIM_USER_DETAILS);
			if (obj instanceof String) {
				userDetails = objectMapper.readValue(((String) obj), User.class);
			}
		} catch (Exception e) {
			logger.error("Error whule getting user details from token due to: " + e.getMessage(), e);
			throw new ClinicException(e, "Error whule getting user details from token due to: " + e.getMessage());
		}
		return userDetails;
	}

	public String getUsernameFromToken(String token) {
		String username;
		try {
			final Claims claims = getClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = getClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (Exception e) {
			expiration = null;
		}
		return expiration;
	}

	private Claims getClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	private Date generateExpirationDate() {
		return new Date(System.currentTimeMillis() + expiration * 1000);
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails userDetails) throws AuthenticationException {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
		claims.put(CLAIM_KEY_ROLES, userDetails.getAuthorities());
		claims.put(CLAIM_KEY_CREATED, new Date());
		try {
			claims.put(CLAIM_USER_DETAILS, objectMapper.writeValueAsString(userDetails));
		} catch (JsonProcessingException e) {
			throw new InsufficientAuthenticationException("Unable to write user details in token");
		}
		return generateToken(claims);
	}

	String generateToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String refreshToken(String token) {
		String refreshedToken;
		try {
			final Claims claims = getClaimsFromToken(token);
			claims.put(CLAIM_KEY_CREATED, new Date());
			refreshedToken = generateToken(claims);
		} catch (Exception e) {
			refreshedToken = null;
		}
		return refreshedToken;
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}
	
	/*public static void main(String[] args) {
		
		JwtTokenUtil JwtTokenUtil=new JwtTokenUtil();
		JwtUser jwt=new JwtUser();
		jwt.setPassword("Amruta@123");
		jwt.setUserId(10L);
		jwt.setUsername("Amruta");
		String token = JwtTokenUtil.generateToken(jwt);
		System.out.println(token);
	}*/
}
