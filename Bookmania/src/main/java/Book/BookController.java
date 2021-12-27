package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
    private final String XML_APPLICATION_CONTEXT_FILE = "applicationContext.xml";

    @RequestMapping("/showForm")
    public String showBookForm(Model model){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(XML_APPLICATION_CONTEXT_FILE);

        BookDetailInterface bookInfo = context.getBean("bookDetail",BookDetailInterface.class);
        log.info("Successfully created the bean for Book");

        model.addAttribute("book", bookInfo);

        return "book-form";
    }

    @RequestMapping("/processForm")
    public String processBookForm(@ModelAttribute("book") BookDetail Book){

        //log the Input Data
        log.info("Book Details: " + Book.getTitleOfTheBook() + " " + Book.getFirstName() + " " + Book.getLastName());

        return "book-processor";
    }
}
