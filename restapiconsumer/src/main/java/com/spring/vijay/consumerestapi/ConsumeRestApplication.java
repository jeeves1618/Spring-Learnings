package com.spring.vijay.consumerestapi;

import com.spring.vijay.restservice.DomainController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = DomainController.class)
public class ConsumeRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumeRestApplication.class, args);
	}
}
