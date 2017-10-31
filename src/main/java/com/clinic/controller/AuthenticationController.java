package com.clinic.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.model.JwtUser;
import com.clinic.vos.JwtAuthRequestVO;


@RestController
@RequestMapping(value="/clinic")
public class AuthenticationController {	
	
	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	/*@Autowired
	private UserDetailsService userDetailsService;
	*/
	@Autowired
	JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "auth", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthRequestVO authenticationRequest) throws AuthenticationException {

		// Perform the security
		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		//final JwtUser userDetails = (JwtUser) userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
	
		JwtUser userDetails=new JwtUser();
		userDetails.setPassword("Amruta@123");
		userDetails.setUserId(10L);
		userDetails.setUsername("Amruta");
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		System.out.println(token);

		// Return the user details and token
		HttpHeaders headers = new HttpHeaders();
		headers.add(tokenHeader, token);
		return ResponseEntity.ok().headers(headers).body(authentication.getPrincipal());
	}
	
	/*@RequestMapping(value = "refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String refreshedToken = jwtTokenUtil.refreshToken(token);
		return ResponseEntity.ok(new JwtAuthenticationResponseVO(refreshedToken));
	}

	@RequestMapping(value = "auth/users/forgotPassword/{username}", method = RequestMethod.PUT)
	public ResponseVO forgotPassword(@PathVariable(value = "username") String username) {
		return userService.forgotPassword(username);
	}*/
	
}
