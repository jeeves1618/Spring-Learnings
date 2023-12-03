package net.myphenotype.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoanController {

    @GetMapping("/myloan")
    public String getLoanInfo(){
        return "Loan information retrieved from database";
    }
}
