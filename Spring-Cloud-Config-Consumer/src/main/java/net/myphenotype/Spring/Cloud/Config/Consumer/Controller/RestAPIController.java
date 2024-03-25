package net.myphenotype.Spring.Cloud.Config.Consumer.Controller;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Spring.Cloud.Config.Consumer.Configuration.ReadData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@EnableConfigurationProperties(value = {ReadData.class})
public class RestAPIController {

    @Autowired
    ReadData readData;

    @Value("${myreads.url:prod}")
    String urlToBeAccessed;

    @GetMapping(path = "/hello")
    public String sayHello(){

        return "Hello World! Here is your way to wisdom " + urlToBeAccessed + " " + readData.getWhatdoimean();
    }

    @GetMapping("/contact-info")
    public ResponseEntity<ReadData> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(readData);
    }

    @GetMapping("/url")
    public ResponseEntity<String> getUrlInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(readData.getUrl());
    }
}
