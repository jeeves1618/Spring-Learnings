package net.myphenotype.RestEndPoints.Controller;

import net.myphenotype.RestEndPoints.Domain.Cricketer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("/Controller")
public class MyController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello-World";
    }

    @GetMapping
    public String defaultPage(Model model){
        return "index";
    }

    @GetMapping("/cricketer")
    public String getCricketer(Model model){
        Cricketer cricketer = new Cricketer("Dean","Jones","Australia",5549,14);
        model.addAttribute("cricketer",cricketer);
        return "cricketer";
    }


}
