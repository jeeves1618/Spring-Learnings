package Librarian.BookManager.Controller;

import Librarian.BookManager.Entity.*;
import Librarian.BookManager.Service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    private static final String APPLICATION_CONTEXT_XML= "applicationContext.xml";
    /*
    Injecting the BookDAO.
    Autowiring will result in Spring scanning for a component that implements
    Customer DAO interface.
     */
    @Autowired
    private BookService bookService;
    @Autowired
    private BookSummary bookSummary;

    @GetMapping(path = "/list")
    public String listBooks(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        List<BookExpanded> bookList = bookService.getBooks();

        model.addAttribute("books",bookList);
        model.addAttribute("bookSummary",bookSummary);

        return "List-Books";
    }

    @GetMapping(path = "/showFormForAdding")
    public String ShowFormForAdding(Model model){

        log.info("Creating a new student object. ");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

        Book newBookToBeAdded = context.getBean("book", Book.class);
        BookDetail newDetailsToBeAdded = context.getBean("bookDetail", BookDetail.class);
        BookAuthor newAuthorToBeAdded = context.getBean("bookAuthor",BookAuthor.class);

        /*
        We pass two parameters to the addAttribute method; name and value.
        Name will be used in the JSP/HTML/Angular/React as modelAttribute
        The value (i.e the bean created) will bind the UI layer to the underlying data object.
         */
        model.addAttribute("book",newBookToBeAdded);

        return "Book-Form";
    }

    @PostMapping(path = "/addBook")
    public String AddBookToList(@ModelAttribute("book") Book book){
        bookService.saveBook(book);
        return "redirect:/book/list";
    }

    @GetMapping(path = "/showFormForUpdating")
    public String ShowFormForUpdate(@RequestParam("bookID") int theID, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        Book bookToBeUpdated = bookService.getBookbyID(theID);

        //Set the Customer as the Model Attribute to Prepopulate the Form
        model.addAttribute("book",bookToBeUpdated);

        //Send the data to the right form
        return "Book-Form";
    }

    @GetMapping(path = "/delete")
    public String DeleteBook(@RequestParam("bookID") int theID, Model model){
        //Delete the Book

        bookService.deleteBookById(theID);
        return "redirect:/book/list";
    }

    @GetMapping(path = "/search")
    public String SearchBookByPartialName(@RequestParam("theSearchName") String bookPartialName, Model model){
        log.info("Search Pattern: " + bookPartialName);
        List<BookExpanded> bookList = bookService.getBooksByPartialName(bookPartialName);

        model.addAttribute("books",bookList);

        return "List-Books";
    }
}
