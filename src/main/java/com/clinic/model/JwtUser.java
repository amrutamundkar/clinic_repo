package com.clinic.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtUser implements UserDetails {
	
	private static final long serialVersionUID = 8194630483264464352L;

	private Long userId;
	
	private boolean enabled;

	@JsonIgnore
	private String password;

	private String username;

	private String securityQuestion;

	private String securityAuestion;	

	public JwtUser() {
		super();
	}

	public JwtUser(Long userId, String password, String username, String securityQuestion, String securityAuestion) {
		super();
		this.userId = userId;
		this.password = password;
		this.username = username;
		this.securityQuestion = securityQuestion;
		this.securityAuestion = securityAuestion;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAuestion() {
		return securityAuestion;
	}

	public void setSecurityAuestion(String securityAuestion) {
		this.securityAuestion = securityAuestion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}
	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	
	
	
	
	
	


}
