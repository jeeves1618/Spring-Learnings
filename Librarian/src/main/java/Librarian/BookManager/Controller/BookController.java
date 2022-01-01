package Librarian.BookManager.Controller;

import Librarian.BookManager.DAO.BookDAO;
import Librarian.BookManager.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    /*
    Injecting the BookDAO.
    Autowiring will result in Spring scanning for a component that implements
    Customer DAO interface.
     */
    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(path = "/list")
    public String listBooks(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        List<Book> bookList = bookDAO.getBooks();

        model.addAttribute("books",bookList);

        return "List-Books";
    }
}
