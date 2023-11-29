package net.myphenotype.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @GetMapping("/myaccount")
    public String getAccountInfo(){
        return "Account information retrieved from database";
    }
}
