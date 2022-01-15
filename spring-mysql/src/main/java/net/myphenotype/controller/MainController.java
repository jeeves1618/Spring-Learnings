package net.myphenotype.controller;

import net.myphenotype.entity.Book;
import net.myphenotype.entity.BookDetail;
import net.myphenotype.repository.BookDetailRepository;
import net.myphenotype.repository.BookRepository;
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
    private BookRepository bookRepository;

    @Autowired
    private BookDetailRepository bookDetailRepository;

    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Book> getBooks(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */

        Iterable<Book> bookList = bookRepository.findAll();

        /* model.addAttribute("books",bookList);

        return "List-Books";

         */
        return bookList;
    }

    @GetMapping(path = "/listdetail")
    public @ResponseBody Iterable<BookDetail> getBooksList(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */

        Iterable<BookDetail> bookList = bookDetailRepository.findAll();

        return bookList;
    }
}
