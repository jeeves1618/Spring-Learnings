package net.myphenotype.Spring.Security.controller;

import net.myphenotype.Spring.Security.entity.Notices;
import net.myphenotype.Spring.Security.repository.NoticesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class NoticeController {

    @Autowired
    NoticesRepository noticesRepository;

    @GetMapping("/mynotice")
    public ResponseEntity<List<Notices>> getNoticeInfo(){
        List<Notices> notices = noticesRepository.findAll();
        if (notices != null){
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES)).body(notices);
        } else {
            return null;
        }
    }
}
