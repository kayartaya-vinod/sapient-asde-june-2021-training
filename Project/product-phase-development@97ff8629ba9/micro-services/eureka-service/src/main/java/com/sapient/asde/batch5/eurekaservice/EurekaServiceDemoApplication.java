package com.sapient.asde.batch5.eurekaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceDemoApplication.class, args);
	}

}
