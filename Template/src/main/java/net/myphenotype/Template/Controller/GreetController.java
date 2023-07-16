package net.myphenotype.Template.Controller;

import net.myphenotype.Template.Domain.Greeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetController {
    @Autowired
    Greeter greeter;

    @RequestMapping("/")
    public String greetUser(){
        return "Hello World!";
    }

    @RequestMapping("/machine")
    public Greeter greetMachine(){
        greeter.setGreeting("Hello Matrix");
        return greeter;
    }
}
