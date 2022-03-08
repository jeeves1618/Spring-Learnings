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
        log.info("Throwing the Not Found");
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
        log.info("Attempting to Add");
        bookService.saveBook(bookExpanded);
        return bookExpanded;
    }

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

    @GetMapping(path = "/delete")
    public String DeleteBook(@RequestParam("bookID") int theID, Model model){
        //Delete the Book
        log.info("The ID of book to be deleted : " + theID);
        bookService.deleteBookById(theID);
        return "redirect:/book/list";
    }

    @GetMapping(path = "/search")
    public String SearchBookByPartialName(@ModelAttribute("bookSearch") BookSearch bookSearch, Model model){
        log.info("Search Pattern: " + bookSearch.getSearchString());
        List<BookExpanded> bookList = bookService.getBooksByPartialName(bookSearch.getSearchString());

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);

        return "bookList";
    }
}
