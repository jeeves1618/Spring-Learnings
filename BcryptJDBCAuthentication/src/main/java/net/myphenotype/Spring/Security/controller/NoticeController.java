package net.myphenotype.Spring.Security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
    @GetMapping("/mynotice")
    public String getNoticeInfo(){
        return "Notices retrieved from database";
    }
}
