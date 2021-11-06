package com.spring.vijay.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String greetingTemplate = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/sayhello")
    public Greeting greeting(@RequestParam(value = "salutationName", defaultValue = "Fellas!") String salutationName){
        return new Greeting(counter.incrementAndGet(),String.format(greetingTemplate,salutationName));
    }
}
