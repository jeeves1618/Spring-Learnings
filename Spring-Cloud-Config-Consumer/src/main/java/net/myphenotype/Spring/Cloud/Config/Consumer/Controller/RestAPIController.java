package net.myphenotype.Spring.Cloud.Config.Consumer.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestAPIController {

    @Value("${message:default}")
    String urlToBeAccessed;

    @GetMapping(path = "/hello")
    public String sayHello(){

        return "Hello World! Here is your way to wisdom " + urlToBeAccessed ;
    }
}
