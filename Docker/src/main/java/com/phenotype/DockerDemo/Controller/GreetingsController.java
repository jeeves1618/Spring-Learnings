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

6. login from terminal using docker login command
7. Create the image using the command: docker build -t my-first-docker-image .
[the . means take the docker file from the same dire]
8. docker images will list the docker image
9. you can use this docker image to create containers. But, as a standard, lets
first push the image to Docker Hub Registry. If you are working with Azure, you
push it to ACR, Azure Container Registry. If you are working with AWS you push it
to ECR, Elastic Container Registry.

10. Create a docker hub repository.
11. Tag the docker image using
docker tag my-first-docker-image:latest <account name>/my-first-docker-image:v3
12. now we can push the image to the docker hub
docker push vjponnusamy/my-first-docker-image
13. In the real world, the image will be pushed by a CI pipeline
14. The image will be pulled by a CD pipeline
15. docker rmi <image-name> will remove the image
16. We can run the app with the command docker run -p 8090:8080  <account name>/my-first-docker-image
17. docker ps will list all containers
18. docker kill <container id> will kill the running container
*/