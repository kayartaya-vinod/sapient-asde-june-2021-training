package com.sapient.asde.batch5.emailservice;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EmailServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServiceApplication.class, args);

	}

	@Bean
	TypeReference<HashMap<String, Object>> typeRef() {
		return new TypeReference<HashMap<String, Object>>() {
		};
	}

}
