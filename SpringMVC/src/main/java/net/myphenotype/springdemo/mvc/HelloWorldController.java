package net.myphenotype.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class HelloWorldController {

    //Method to show the initial form

    @RequestMapping("/showForm")
    public String showForm(){
        return "hello-world-form";
    }

    @RequestMapping("/processForm")
    public String processForm(){
        return "process-Form";
    }

    @RequestMapping("/getFortune")
    public String ThoughtOftheDay(HttpServletRequest request, Model model){
        List<String> listOfThoughts = new ArrayList<>();
        listOfThoughts.add("No snowflake in an avalanche ever feels responsible for the destruction");
        listOfThoughts.add("If you eat something and nobody sees you eat it, it has no calories");
        listOfThoughts.add("You don't have to be faster than the tiger, you just have to faster than the slowest guy running away from it");
        listOfThoughts.add("Three of you can keep a secret, if you can get rid of other two");
        listOfThoughts.add("A real citizen is someone who gets a speeding ticket for driving little above the limit and rejoices that the system works");

        Random random = new Random();

        String theName = request.getParameter("associateName");

        //Create the message
        String result = "Hello " + theName + ", your thought for the day is : " + listOfThoughts.get(random.nextInt(0,listOfThoughts.size()-1));

        model.addAttribute("thoughtForTheDay",result);

        return "get-Fortune";
    }
}
