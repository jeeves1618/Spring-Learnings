package net.myphenotype.currencyconverter.classes.controller;

import net.myphenotype.currencyconverter.classes.service.Converter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/main")
public class MainController {

    @GetMapping("/data")
    public String getConverterData() throws IOException {
        float rupee = Converter.amountInRupee("USD",100);
        System.out.println("Amount in Rupee is : " + rupee);
        return "Converter data";
    }
}
