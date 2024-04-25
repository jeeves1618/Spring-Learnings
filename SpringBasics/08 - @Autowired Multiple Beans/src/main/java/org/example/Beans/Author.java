package org.example.Beans;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Author {

    private String authorName;

    private Book book;
    @Autowired
    public Author(Book book) {
        this.book = book;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @PostConstruct
    public void postConstruct(){
        System.out.println("This gets executed right after the author bean was created during startup");
        if (this.getAuthorName() == null) setAuthorName ("Ayn Rand");
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorName='" + authorName + '\'' +
                ", book=" + book +
                '}';
    }
}
