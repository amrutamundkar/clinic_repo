package com.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.clinic.controller.AuthenticationController;
import com.clinic.controller.JwtTokenUtil;

@SpringBootApplication
public class ClinicApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}
	
	@Bean
	public JwtTokenUtil getjwtUtils(){
		return new JwtTokenUtil();
	}
	
	
}



