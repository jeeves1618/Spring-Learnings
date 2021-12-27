package net.myphenotype.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class AnnotatedController {

    @RequestMapping("/getFortuneAnnotated")
    public String ThoughtOftheDay(@RequestParam("associateName") String theName, HttpServletRequest request, Model model){
        List<String> listOfThoughts = new ArrayList<>();
        listOfThoughts.add("No snowflake in an avalanche ever feels responsible for the destruction");
        listOfThoughts.add("If you eat something and nobody sees you eat it, it has no calories");
        listOfThoughts.add("You don't have to be faster than the tiger, you just have to faster than the slowest guy running away from it");
        listOfThoughts.add("Three of you can keep a secret, if you can get rid of other two");
        listOfThoughts.add("A real citizen is someone who gets a speeding ticket for driving little above the limit and rejoices that the system works");
        listOfThoughts.add("Laziness is nothing more than the habit of resting before you get tired");
        listOfThoughts.add("It takes considerable knowledge just to realize the extent of your own ignorance");
        listOfThoughts.add("People who think they know everything are a great annoyance to those of us who do.");
        listOfThoughts.add("It is even harder for the average ape to believe that he has descended from man");

        Random random = new Random();
        /*
        String theName = request.getParameter("associateName");
        */
        //Create the message
        String result = "Hello " + theName + ", your thought for the day is : " + listOfThoughts.get(random.nextInt(0,listOfThoughts.size()-1));

        model.addAttribute("thoughtForTheDay",result);

        return "get-Fortune";
    }
}
