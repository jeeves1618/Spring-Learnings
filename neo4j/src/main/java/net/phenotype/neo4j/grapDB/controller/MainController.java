package net.phenotype.neo4j.grapDB.controller;

import net.phenotype.neo4j.grapDB.domain.CreateAuthorRequest;
import net.phenotype.neo4j.grapDB.domain.GetAuthorsByBirthYear;
import net.phenotype.neo4j.grapDB.domain.UpdateAuthorRequest;
import net.phenotype.neo4j.grapDB.entity.Author;
import net.phenotype.neo4j.grapDB.entity.Book;
import net.phenotype.neo4j.grapDB.service.AuthorService;
import net.phenotype.neo4j.grapDB.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    AuthorService authorService;

    @Autowired
    BookService bookService;

    @PostMapping("/author/create")
    public Author createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest){
        return authorService.createAuthor(createAuthorRequest);
    }
    /*
    http://localhost:8080/api/Author/6
     */
    @GetMapping("/Author/{id}")
    public Author getAuthorById(@PathVariable Long id){
        return authorService.getAuthorById(id);
    }
    /*
    http://localhost:8080/api/getAuthorByName/Robert/Caro
     */
    @GetMapping("/getAuthorByName/{fName}/{lName}")
    public List<Author> getAuthorByName(@PathVariable("fName") String firstName, @PathVariable("lName") String lastName){
        return authorService.getAuthorByName(firstName, lastName);
    }
    @GetMapping("/Authors")
    public List<Author> getAuthors(){
        return authorService.getAuthors();
    }

    @GetMapping("/Books")
    public List<Book> getBooks(){
        return bookService.findAll();
    }
    /*
    http://localhost:8080/api/Author
     */
    @PutMapping("/Author")
    public Author updateAuthor(@RequestBody UpdateAuthorRequest updateAuthorRequest){
        return authorService.updateAuthor(updateAuthorRequest);
    }

    @DeleteMapping("/Author/{id}")
    public String deleteAuthorById(@PathVariable Long id){
        return authorService.deleteAuthor(id);
    }
    /*
    End Point: http://localhost:8080/api/Author/byYear

    Request Body:
    -------------
    {
        "bornYearList": [1951,1936]
    }
     */

    @GetMapping("/Author/byYear")
    public List<Author> getAuthorByYear(@RequestBody GetAuthorsByBirthYear yearList){
        System.out.println("Got the request");
        return authorService.getAuthorInBorn(yearList.getBornYearList());
    }
}
