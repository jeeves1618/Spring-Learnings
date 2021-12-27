package udemy.learnspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
    @ResponseBody
    @GetMapping("/hello")
    public String getHello(){
        return "hello";
    }
}
