package org.example.Configs;

import org.example.Beans.Author;
import org.example.Beans.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class ProjectConfiguration {

    @Bean
    public Book book(){
        Book book = new Book();
        book.setTitle("Atlas Shrugged");
        return book;
    }

    @Bean
    //This is where wiring of one bean happens with another bean through method parameter
    public Author author(Book book){
        Author author = new Author();
        author.setAuthorName("Ayn Rand");
        author.setBook(book);
        return author;
    }

}
