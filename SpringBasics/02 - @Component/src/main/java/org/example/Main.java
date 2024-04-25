package org.example;

import org.example.Beans.Book;
import org.example.Configs.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        Book book01 = context.getBean(Book.class);
        /*
        Unlike @Bean annotation where we can control the title of the book,
        @Component will just return null values.
         */
        System.out.println(book01.getTitle());
        System.out.println(book01.toString());
    }
}