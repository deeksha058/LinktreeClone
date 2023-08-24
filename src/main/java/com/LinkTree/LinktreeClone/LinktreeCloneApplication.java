package com.LinkTree.LinktreeClone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableWebSecurity
@EnableSwagger2
@Configuration
public class LinktreeCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinktreeCloneApplication.class, args);

		System.out.println("Run Successfully!");
	}
}
