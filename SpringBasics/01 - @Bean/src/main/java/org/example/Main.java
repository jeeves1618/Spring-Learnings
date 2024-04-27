package org.example;

import org.example.Beans.Book;
import org.example.Configs.ProjectConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        Book book01 = context.getBean(Book.class);
        System.out.println(book01.getTitle());


        book01 = context.getBean("book01",Book.class);
        System.out.println(book01.getTitle());

        book01 = context.getBean("designAsArt",Book.class);
        System.out.println(book01.getTitle());
    }
}