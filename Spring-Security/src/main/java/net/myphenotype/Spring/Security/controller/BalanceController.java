package net.myphenotype.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    @GetMapping("/mybalance")
    public String getBalanceInfo(){
        return "Balance information retrieved from database";
    }
}
