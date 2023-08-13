package com.LinkTree.LinktreeClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class LinktreeCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinktreeCloneApplication.class, args);

		System.out.println("Run Successfully!");
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
