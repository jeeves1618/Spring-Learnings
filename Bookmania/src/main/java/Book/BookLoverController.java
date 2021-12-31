package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/bookLover")
public class BookLoverController {
    private final String XML_APPLICATION_CONTEXT_FILE = "applicationContext.xml";

    /*
    InitBinder is added to trim leading and lagging white spaces
     */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
    @RequestMapping("/showForm")
    public String showBookForm(Model model){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(XML_APPLICATION_CONTEXT_FILE);

        BookLover bookLover = context.getBean("bookLoverHistoryBuffPersona",BookLover.class);
        log.info("Successfully created the bean for Book Lover");
        model.addAttribute("bookLover", bookLover);

        return "book-lover-form";
    }

    @RequestMapping("/processForm")
    public String processBookForm(@Valid @ModelAttribute("bookLover") BookLoverHistoryBuffPersona bookLover,
            BindingResult bindingResult){

        //log the Input Data
        if(bindingResult.hasErrors()){
            return "book-lover-form";
        }
        else {
            return "book-lover-processor";
        }
    }
}
