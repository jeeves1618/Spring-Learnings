package org.example.Configs;

import org.example.Beans.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProjectConfiguration {

    @Bean
    public Book book01(){
        Book book = new Book();
        book.setTitle("Thinking Fast and Slow");
        return book;
    }

    @Bean(name = "designAsArt")
    public Book book02(){
        Book book = new Book();
        book.setTitle("Design as Art");
        return book;
    }

    @Bean
    @Primary
    public Book book03(){
        Book book = new Book();
        book.setTitle("Creative Confidence");
        return book;
    }
}
