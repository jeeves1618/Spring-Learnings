package com.vijay.servewebcontent;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class WebController {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/getthegreeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model, RestTemplate restTemplate) {
        Domain domain= restTemplate.getForObject(
                "http://localhost:8081/sayhello?salutationName=Rains", Domain.class);
        model.addAttribute("name", name);
        model.addAttribute("content",domain.getContent());
        return "greeting";
    }
}
