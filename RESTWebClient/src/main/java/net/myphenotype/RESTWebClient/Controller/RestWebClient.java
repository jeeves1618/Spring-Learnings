package net.myphenotype.RESTWebClient.Controller;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.RESTWebClient.Domain.BookExpanded;
import net.myphenotype.RESTWebClient.Domain.BookSearch;
import net.myphenotype.RESTWebClient.Domain.BookSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api")
public class RestWebClient {

    @Autowired
    private BookSummary bookSummary;

    @Autowired
    private BookExpanded book;

    @Autowired
    private BookSearch bookSearch;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/list")
    public String getBookList(Model model, RestTemplate restTemplate){

        //Iterable<BookExpanded> bookList = bookService.listBooks();
        //Making the call to REST Engpoint
        ResponseEntity<List<BookExpanded>> responseEntity = restTemplate.exchange("http://localhost:8080/api/books/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BookExpanded>>() {});
        //Getting the body of the response returned by REST Service
        List<BookExpanded> bookList = responseEntity.getBody();

        BookSummary bookSummary = restTemplate.getForObject("http://localhost:8080/api/books/summary",BookSummary.class);
        BookSearch bookSearch = restTemplate.getForObject("http://localhost:8080/api/books/search",BookSearch.class);

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);
        model.addAttribute("bookSearch",bookSearch);

        return "bookList";
    }

    @GetMapping(path = "/showFormForAdding")
    public String ShowFormForAdding(Model model){

        log.info("Showing form for the creation of new student object. ");

        model.addAttribute("book",book);

        return "bookForm";
    }

    @PostMapping(path = "/addBook")
    public String AddBookToList(@ModelAttribute("book") BookExpanded book, RestTemplate restTemplate){
        //bookService.saveBook(book);
        if(book.getId() == null)
            restTemplate.postForEntity("http://localhost:8080/api/books",book,String.class);
        else
            restTemplate.put("http://localhost:8080/api/books",book);
        return "redirect:/api/list";
    }

    @GetMapping(path = "/showFormForUpdating")
    public String ShowFormForUpdate(@RequestParam("bookID") int theID, Model model, RestTemplate restTemplate){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        BookExpanded bookToBeUpdated = restTemplate.getForObject("http://localhost:8080/api/books/"+String.valueOf(theID),BookExpanded.class);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeUpdated);

        //Send the data to the right form
        return "bookForm";
    }

    @GetMapping(path = "/showDetail")
    public String ShowDetail(@RequestParam("bookID") int theID, Model model,RestTemplate restTemplate){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        BookExpanded bookToBeDisplayed = restTemplate.getForObject("http://localhost:8080/api/books/"+String.valueOf(theID),BookExpanded.class);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeDisplayed);

        //Send the data to the right form
        return "bookDetail";
    }

    @GetMapping(path = "/showFormForDeleting")
    public String ShowFormForDelete(@RequestParam("bookID") int theID, Model model, RestTemplate restTemplate){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        BookExpanded bookToBeDeleted = restTemplate.getForObject("http://localhost:8080/api/books/"+String.valueOf(theID),BookExpanded.class);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeDeleted);
        log.info(bookToBeDeleted.toString());

        //Send the data to the right form
        return "deleteForm";
    }

    @GetMapping(path = "/delete")
    public String DeleteBook(@RequestParam("bookID") int theID, Model model, RestTemplate restTemplate){
        //Delete the Book
        log.info("The ID of book to be deleted : " + theID);
        restTemplate.delete("http://localhost:8080/api/books/"+String.valueOf(theID));
        return "redirect:/api/list";
    }

    @GetMapping(path = "/search")
    public String SearchBookByPartialName(@ModelAttribute("bookSearch") BookSearch bookSearch, Model model, RestTemplate restTemplate){
        log.info("Search Pattern: " + bookSearch.getSearchString());
        ResponseEntity<List<BookExpanded>> responseEntity = restTemplate.exchange("http://localhost:8080/api/search/"+bookSearch.getSearchString(), HttpMethod.GET, null,
                new ParameterizedTypeReference<List<BookExpanded>>() {});
        //Getting the body of the response returned by REST Service
        List<BookExpanded> bookList = responseEntity.getBody();

        BookSummary bookSummary = restTemplate.getForObject("http://localhost:8080/api/books/summary",BookSummary.class);

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);

        return "bookList";
    }
}
