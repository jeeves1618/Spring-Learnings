package net.myphenotype.Configurations.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.myphenotype.Configurations.Entity.Book;
import net.myphenotype.Configurations.Entity.BookExpanded;
import net.myphenotype.Configurations.Entity.Topic;
import net.myphenotype.Configurations.Repository.BookRepository;
import net.myphenotype.Configurations.Repository.TopicRepository;
import net.myphenotype.Configurations.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class MainController {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    BookService bookService;

    @GetMapping("/mynotice")
    public String getMyNotice(){
        System.out.println("/mynotice got invoked");
        return "This is the compilation of books I have read and liked. These books spread across an wide array of topics. My aimless meanderings across multiple subjects may resemble a cobweb spun by a drunken spider. Yet, I have thoroughly enjoyed this journey. These books have also vastly enhanced my ability to bore people to death over a cup of coffee. This portal is my vainglorious attempt to handhold those who are looking forward to embark on a similar journey. ";
    }

    @GetMapping("/mybooks")
    public List<Book> getMyBooks(){
        System.out.println("/mybooks got invoked");
        List<Book> bookList = bookRepository.findAll();
        return bookList;
    }

    @GetMapping("/mygenre")
    public List<Topic> getMyGenre(){
        System.out.println("/mygenre got invoked");
        List<Topic> bookList = topicRepository.findAll();
        return bookList;
    }

    @GetMapping(path = "/myupload")
    public String uploadFromJson(){
        System.out.println("/myupload got invoked");
        String responseMessage = "Data uploaded successfully into Book and Topic Tables.";
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
            System.out.println("Unsuccessful write of JSON");
            responseMessage = "Unsuccessful write of JSON for books";
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
            System.out.println("Unsuccessful read of JSON for topics");
            if (responseMessage.equals("Unsuccessful write of JSON for books"))
                responseMessage = "Unsuccessful write of JSON for books and topics";
            else
                responseMessage = "Unsuccessful write of JSON for topics";
            e.printStackTrace();
        }
        bookService.saveAll(topicList);

        return responseMessage;
    }
}
