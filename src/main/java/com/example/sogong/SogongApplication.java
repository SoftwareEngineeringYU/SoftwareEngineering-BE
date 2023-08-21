package com.example.sogong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SogongApplication {

	public static void main(String[] args) {
		SpringApplication.run(SogongApplication.class, args);
	}

}
