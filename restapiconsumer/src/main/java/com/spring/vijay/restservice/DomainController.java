package com.spring.vijay.restservice;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class DomainController {
    private static final String greetingTemplate = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/gethello")
    public Domain domain(RestTemplate restTemplate){
        Domain domain = restTemplate.getForObject(
                "http://localhost:8081/sayhello?salutationName=Planet%20Earth", Domain.class);
        //model.addAttribute("domain",domain);
        return domain;
    }
}
