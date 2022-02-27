package net.myphenotype.RestEndPoints.Controller;

import net.myphenotype.RestEndPoints.Domain.Cricketer;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
@RequestMapping("/RestEndPoints")
public class MyRestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello-World";
    }

    @GetMapping
    public String defaultPage(Model model){
        return "cricketer";
    }

    @GetMapping("/cricketer")
    public Cricketer getCricketer(){
        Cricketer cricketer = new Cricketer("Dean","Jones","Australia",5549,14);
        return cricketer;
    }

}
