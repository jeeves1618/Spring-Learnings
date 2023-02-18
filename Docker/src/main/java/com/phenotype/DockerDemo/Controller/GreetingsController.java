package com.phenotype.DockerDemo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreetingsController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Welcome to the dovey world of dockers!";
    }
}
/*

Now here is what we have to do to "Dokerize" this spring boot app
1. Create Docker File
2. Using the instructions in the Docker File, create a Docker image
3. Push Docker Image to Docker hub repository
4. pull Docker Image from docker hub repository
5. Run the pulled Docker image in our local computer
 */