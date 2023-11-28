package net.myphenotype.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @GetMapping("/mycontact")
    public String getContactInfo(){
        return "Contact information retrieved from database";
    }
}
