package com.sapient.asde.batch5.vehicledataservice;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class VehicleDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleDataServiceApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	TypeReference<HashMap<String, Object>> typeRef() {
		return new TypeReference<HashMap<String, Object>>() {
		};

	}
}
