package net.myphenotype.Librarian.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Entity.*;
import net.myphenotype.Librarian.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/book")
@Validated
public class MainController {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookSummary bookSummary;

    @Autowired
    private Book book;

    @Autowired
    private BookDetail bookDetail;

    @Autowired
    private BookSearch bookSearch;

    @Autowired
    private Readings readings;

    @GetMapping(path = "/listjason")
    public @ResponseBody
    Iterable<BookExpanded> getBooks(Model model, HttpServletRequest request){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        Iterable<BookExpanded> bookList = bookService.listBooks(request.getRequestURI().toString());

        /* model.addAttribute("books",bookList);

        return "List-Books";

         */

        return bookList;
    }

    @GetMapping("/list")
    @PreAuthorize("hasIpAddress('127.0.0.1')")
    public String getBookList(Model model, HttpServletRequest request){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        List<Authors> authorsList = new ArrayList<>();

        Iterable<BookExpanded> bookList = bookService.listBooks(request.getRequestURI().toString());

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);
        model.addAttribute("bookSearch",bookSearch);

        return "bookList";
    }
    @GetMapping(path = "/books")
    public String listBooksUnderTopic(@RequestParam("Genre") String Genre, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        List<BookExpanded> books = bookService.findBooksByGenre(Genre);
        if(Genre.contains(" ("))
            Genre = Genre.substring(0,Genre.indexOf(" ("));
        String genreMessage = "You have " + bookSummary.getNumberOfBooks() + " books under " + Genre.toLowerCase() + " acquired at the cost of " + bookSummary.getTotalCostOfBooks();
        model.addAttribute("genre",Genre);
        model.addAttribute("genreMessage",genreMessage);
        model.addAttribute("books",books);
        model.addAttribute("bookSummary",bookSummary);
        //Send the data to the right form
        return "books";
    }

    @GetMapping("/main")
    public String getBookMain(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        List<Topic> topicSummaries = bookService.findCountByTopics();
        System.out.println(topicSummaries);
        model.addAttribute("topics",topicSummaries);
        return "main";
    }

    @GetMapping("/v1/books")
    public String getBook(Model model, HttpServletRequest request){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */

        List<Authors> authorsList = new ArrayList<>();

        Iterable<BookExpanded> bookList = bookService.listBooks(request.getRequestURI().toString());

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);
        model.addAttribute("bookSearch",bookSearch);

        return "bookList";
    }

    @GetMapping(path = "/showFormForAdding")
    public String ShowFormForAdding(Model model){

        log.info("Creating a new student object. ");

        /*
        We pass two parameters to the addAttribute method; name and value.
        Name will be used in the JSP/HTML/Angular/React as modelAttribute
        The value (i.e the bean created) will bind the UI layer to the underlying data object.
         */
        model.addAttribute("book",book);

        return "bookForm";
    }

    @PostMapping(path = "/addBook")
    public String addBookToList(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        bookService.saveTopic(book);
        return "redirect:/book/list";
    }

    @PostMapping(path = "/addReading")
    public String addReadingInstance(@ModelAttribute("reading") Readings readings){
        log.info("Readings: " + readings.toString());
        BookExpanded bookExpanded = bookService.getBook(readings.getBook().getId());
        log.info("Before Book Service : " + bookExpanded.toString());
        bookService.saveBook(bookExpanded, readings);
        log.info("After Book Service");
        return "redirect:/book/showDetail?bookID=" + readings.getBook().getId();
    }


    @GetMapping(value = {"/downloadList",  "/downloadReadList"})
    public String downloadToJson(Model model, HttpServletRequest request){

        log.info("Downloading data to JSON file. ");
        /*
        We pass two parameters to the addAttribute method; name and value.
        Name will be used in the JSP/HTML/Angular/React as modelAttribute
        The value (i.e the bean created) will bind the UI layer to the underlying data object.
         */
        Iterable<BookExpanded> bookList = bookService.listBooks(request.getRequestURI().toString());

        ObjectMapper mapper = new ObjectMapper();
        File file;
        if (request.getRequestURI().toString().equals("/book/downloadList"))
            file = new File("src/main/resources/book-list.json");
        else
            file = new File("src/main/resources/read-list.json");

        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(file, bookList);
        } catch (IOException e) {
            log.error("Unsuccessful write of JSON");
            e.printStackTrace();
        }

        List<Topic> topicList = bookService.downloadTopics();
        File topicFile = new File("src/main/resources/topic-list.json");
        try {
            // Serialize Java object info JSON file.
            mapper.writeValue(topicFile, topicList);
        } catch (IOException e) {
            log.error("Unsuccessful write of JSON for topics");
            e.printStackTrace();
        }
        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);
        model.addAttribute("bookSearch",bookSearch);

        return "redirect:/book/list";
    }

    @GetMapping(path = "/uploadList")
    public String uploadFromJson(Model model){

        log.info("Downloading data to JSON file. ");
        /*
        We pass two parameters to the addAttribute method; name and value.
        Name will be used in the JSP/HTML/Angular/React as modelAttribute
        The value (i.e the bean created) will bind the UI layer to the underlying data object.
         */
        Iterable<BookExpanded> bookList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();

        File file = new File("src/main/resources/book-list.json");
        try {
            // Serialize Java object info JSON file.
            bookList = mapper.readValue(file, new TypeReference<List<BookExpanded>>(){});
        } catch (IOException e) {
            log.error("Unsuccessful write of JSON");
            e.printStackTrace();
        }

        for(BookExpanded bookExpanded:bookList){
            bookService.saveBook(bookExpanded);
        }
        List<Topic> topicList = new ArrayList<>();
        File topicFile = new File("src/main/resources/topic-list.json");
        try {
            topicList = mapper.readValue(topicFile, new TypeReference<List<Topic>>(){});
        } catch (IOException e) {
            log.error("Unsuccessful read of JSON for topics");
            e.printStackTrace();
        }
        bookService.saveAll(topicList);
        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);
        model.addAttribute("bookSearch",bookSearch);

        return "redirect:/book/list";
    }

    @GetMapping(path = "/showFormForUpdating")
    public String ShowFormForUpdate(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeUpdated = bookService.getBookbyID(theID);

        //Set the Book as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeUpdated);

        //Send the data to the right form
        return "bookForm";
    }

    @GetMapping(path = "/showFormForReading")
    public String ShowFormForReading(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeUpdated = bookService.getBookbyID(theID);
        readings.setBook(bookToBeUpdated);
        //Set the Expanded Book as the Model Attribute to Prepopulate the Form
        model.addAttribute("reading",readings);
        log.info("Readings: " + readings);

        //Send the data to the right form
        return "readingForm";
    }

    @GetMapping(path = "/showDetail")
    public String ShowDetail(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeDisplayed = bookService.getBookbyID(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeDisplayed);

        //Send the data to the right form
        return "bookDetail";
    }

    @GetMapping(path = "/showFormForDeleting")
    public String ShowFormForDelete(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeDeleted = bookService.getBookbyID(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeDeleted);
        log.info(bookToBeDeleted.toString());

        //Send the data to the right form
        return "deleteForm";
    }

    @GetMapping(path = "/delete")
    public String DeleteBook(@RequestParam("bookID") int theID, Model model){
        //Delete the Book
        log.info("The ID of book to be deleted : " + theID);
        bookService.deleteBookById(theID);
        return "redirect:/book/list";
    }

    @GetMapping(path = "/search")
    public String SearchBookByPartialName(@ModelAttribute("bookSearch") BookSearch bookSearch, Model model){
        log.info("Search Pattern: " + bookSearch.getSearchString() + " and " + bookSearch.getAllTimeGreatsOnly());

        List<BookExpanded> bookList = bookService.getBooksByPartialName(bookSearch.getSearchString(),bookSearch.getAllTimeGreatsOnly());

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);

        return "bookList";
    }
}
