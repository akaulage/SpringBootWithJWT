package com.akaulage.SpringBootWithJWT.SpringBootJWT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SpringBootJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

	//we need password in encoded format so we adding PasswordEncoder method

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}

}
