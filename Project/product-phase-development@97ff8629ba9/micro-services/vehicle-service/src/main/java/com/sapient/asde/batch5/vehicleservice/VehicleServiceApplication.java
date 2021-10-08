package com.sapient.asde.batch5.vehicleservice;

import java.util.HashMap;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sapient.asde.batch5.vehicleservice.cacheconfig.CustomKeyGenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableCaching
@SpringBootApplication
public class VehicleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleServiceApplication.class, args);
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

	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}
}