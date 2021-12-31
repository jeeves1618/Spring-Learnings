package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BookRestController {
    private final String XML_APPLICATION_CONTEXT_FILE = "applicationContext.xml";

    @GetMapping("/processRESTForm")
    public BookDetailInterface processBookForm(@ModelAttribute("book") BookDetail Book){

        //log the Input Data

        return Book;
    }
}
