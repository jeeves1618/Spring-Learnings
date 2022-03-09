package net.myphenotype.SpringAOP.Controller;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.Entity.*;
import net.myphenotype.SpringAOP.ExceptionHandler.BookNotFound;
import net.myphenotype.SpringAOP.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class RestEndPointController {
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

    @GetMapping("/books")
    public Iterable<BookExpanded> getBookList(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */

        List<Authors> authorsList = new ArrayList<>();

        Iterable<BookExpanded> bookList = bookService.listBooks();

        return bookList;
    }

    @GetMapping("/books/{bookId}")
    public BookExpanded getBook(@PathVariable int bookId){
        BookExpanded book = bookService.getBook(bookId);
        if (book == null) {

            throw new BookNotFound("The book with an ID " + bookId + " is not available in the library. Please contact the administrator.");

        }
        return book;
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

    @PostMapping(path = "/books")
    public BookExpanded AddBookToList(@RequestBody BookExpanded bookExpanded){
        bookExpanded.setId(null);
        log.info("Attempting to Add");
        bookService.saveBook(bookExpanded);
        return bookExpanded;
    }

    @PutMapping(path = "/books")
    public BookExpanded UpdateBookToList(@RequestBody BookExpanded bookExpanded){
        log.info("Attempting to Update");
        BookExpanded book = bookService.getBook(bookExpanded.getId());
        if (book == null) {

            throw new BookNotFound("The book with an ID " + bookExpanded.getId() + " is not available in the library. Please contact the administrator.");

        }
        bookService.saveBook(bookExpanded);
        return bookExpanded;
    }
    /*
    Sample Jason Schema for Post and Put
    {
    "id": null,
    "bookTitle": "Monetary Mischief",
    "bookGenre": "Economics",
    "authorFirstName": "Milton",
    "authorLastName": "Friedman",
    "publisherName": "Harcourt Brace",
    "dateOfPurchase": "2006-12-16",
    "costOfPurchase": 14.95,
    "currencyCode": "USD",
    "contactEmail": null,
    "costInLocalCurrency": null,
    "costInLocalCurrencyFmtd": null,
    "shoppingChannel": "Infibeam.com",
    "typeOfBinding": "Paperback",
    "isbNumber":"978-0156619301",
    "authorsFirstName1": "Milton",
    "authorsLastName1": "Friedman",
    "aboutAuthor1": null,
    "authorsFirstName2": "Marsha",
    "authorsLastName2": "Friedman",
    "aboutAuthor2": null,
    "authorsFirstName3": " ",
    "authorsLastName3": " ",
    "aboutAuthor3": null,
    "authorsFirstName4": " ",
    "authorsLastName4": " ",
    "aboutAuthor4": null
}
     */
    @GetMapping(path = "/showFormForUpdating")
    public String ShowFormForUpdate(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeUpdated = bookService.getBookbyID(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeUpdated);

        //Send the data to the right form
        return "bookForm";
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

    @DeleteMapping(path = "/delete/{bookID}")
    public String DeleteBook(@PathVariable("bookID") int theID){
        //Delete the Book. Sample URL: http://localhost:8080/api/delete/57

        BookExpanded book = bookService.getBook(theID);
        if (book == null) {

            throw new BookNotFound("The book with an ID " + theID + " is not available in the library. Please contact the administrator.");

        }
        log.info("The ID of book to be deleted : " + theID);
        bookService.deleteBookById(theID);
        return "The book with " + theID + " is deleted";
    }

    @GetMapping(path = "/search")
    public String SearchBookByPartialName(@ModelAttribute("bookSearch") BookSearch bookSearch, Model model){
        log.info("Search Pattern: " + bookSearch.getSearchString());
        List<BookExpanded> bookList = bookService.getBooksByPartialName(bookSearch.getSearchString());

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);

        return "bookList";
    }

    /*
    Difference between @RequestParam and @PathVariable

    Look at the following request URL:

    http://localhost:8080/tutorials/bookmark/100?site=dineshonjava&id=200

    In the above URL request, the values for site and id can be accessed as below:

    @RequestMapping(value = "/tutorials/bookmark/{siteId}")
    public String bookmark(
    @PathVariable(value="siteId") String siteId
    @RequestParam(value="site", required=true) String site,
    @RequestParam(value="id", required=false) String id){
...
}

     */
}
