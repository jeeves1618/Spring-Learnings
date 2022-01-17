package net.myphenotype.SpringAOP.Controller;

import net.myphenotype.SpringAOP.DAO.BookDao;
import net.myphenotype.SpringAOP.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/book")
public class MainController {
    @Autowired
    private BookDao bookDao;

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<Book> getBooks(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        bookDao.showBooks();
        Iterable<Book> bookList = bookDao.listBooks();

        /* model.addAttribute("books",bookList);

        return "List-Books";

         */
        return bookList;
    }

    @GetMapping("/listweb")
    public String getBookList(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        bookDao.showBooks();
        Iterable<Book> bookList = bookDao.listBooks();

        model.addAttribute("books",bookList);

        return "bookList";
    }
}
