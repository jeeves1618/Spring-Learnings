package net.phenotype.neo4j.grapDB.controller;

import net.phenotype.neo4j.grapDB.domain.CreateAuthorRequest;
import net.phenotype.neo4j.grapDB.entity.Author;
import net.phenotype.neo4j.grapDB.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/author/create")
    public Author createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
        System.out.println("Got the request");
        return authorService.createAuthor(createAuthorRequest);
    }

}
