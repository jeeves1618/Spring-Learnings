package org.example.Configs;

import org.example.Beans.Author;
import org.example.Beans.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "org.example.Beans")
public class ProjectConfiguration {

    @Bean
    public Book fountainHead(){
        Book book = new Book();
        book.setTitle("Fountain Head");
        return book;
    }

    @Bean
    public Book anthem(){
        Book book = new Book();
        book.setTitle("Anthem");
        return book;
    }

    @Bean
    @Primary
    public Book weTheLiving(){
        Book book = new Book();
        book.setTitle("We the living");
        return book;
    }

    @Bean
    public Author aliceOConnor(){
        Author author = new Author(anthem());
        author.setAuthorName("Alice O'Connor");
        return author;
    }

}
