package net.myphenotype.Librarian.Controller;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Domain.SummaryPage;
import net.myphenotype.Librarian.Entity.*;
import net.myphenotype.Librarian.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/app")
public class AppController {
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
    private SummaryPage summaryPage;

    @GetMapping(path = "/books")
    public String listBooksUnderTopic(@RequestParam("Genre") String Genre, Model model){
        //Get the book using the ID from the Service (in turn from DAO and in turn from Table)
        List<BookExpanded> books = bookService.findBooksByGenre(Genre);
        Genre = Genre.substring(0,Genre.indexOf(" ("));
        String genreMessage = "You have " + bookSummary.getNumberOfBooks() + " books under " + Genre.toLowerCase() + " acquired at the cost of " + bookSummary.getTotalCostOfBooks();
        model.addAttribute("genre",Genre);
        model.addAttribute("genreMessage",genreMessage);
        model.addAttribute("books",books);
        model.addAttribute("bookSummary",bookSummary);
        //Send the data to the right form
        return "books";
    }

    @GetMapping(path = "/main")
    public String getBookMain(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        List<Topic> topicSummaries = bookService.findCountByTopics();
        model.addAttribute("topics",topicSummaries);
        return "main";
    }

    @GetMapping(path = "/summary")
    public String getSummary(Model model){
        /*
        This method should do the following.
        1. Get the books from the DAO
        2. And add the books to the model to be diplayed in the view
         */
        String sectionTitle = "Summary of Books";
        String genre = null;
        List<Topic> topics = bookService.findCountByTopics();
        List<SummaryPage> summaryPages = new ArrayList<>();
        List<BookExpanded> books = new ArrayList<>();
        for(Topic topic: topics){
            genre = topic.getBookGenre().substring(0,topic.getBookGenre().indexOf(" ("));
            summaryPage.setBookGenre(genre);
            summaryPage.setBookCount(topic.getBookCount());
            books = bookService.findBooksByGenre(topic.getBookGenre());
            summaryPage.setFormattedAmount(bookSummary.getTotalCostOfBooks());
            summaryPages.add(summaryPage);
            summaryPage = new SummaryPage();
        }
        model.addAttribute("summaryList",summaryPages);
        model.addAttribute("sectionTitle",sectionTitle);
        return "summary";
    }
}
