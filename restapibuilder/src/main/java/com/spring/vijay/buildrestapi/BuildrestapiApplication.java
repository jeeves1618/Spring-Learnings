package com.spring.vijay.buildrestapi;

import com.spring.vijay.restservice.GreetingController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = GreetingController.class)
public class BuildrestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildrestapiApplication.class, args);
	}

}
