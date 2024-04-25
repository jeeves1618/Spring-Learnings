package org.example.Beans;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Author {

    private String authorName;

    private Book book;
    //@Autowired is not required here
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
        this.setAuthorName("Ayn Rand");
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorName='" + authorName + '\'' +
                ", book=" + book +
                '}';
    }
}
