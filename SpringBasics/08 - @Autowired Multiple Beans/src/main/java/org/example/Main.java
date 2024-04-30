package org.example;

import org.example.Beans.Author;
import org.example.Beans.Book;
import org.example.Configs.ProjectConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

@PropertySource("classpath:application.properties")
public class Main {
    @Value("${books.rand.name}")
    static String bookName;

    public static void main(String[] args) {

        System.out.println(bookName);

        var context = new AnnotationConfigApplicationContext(ProjectConfiguration.class);
        /*
        Because we have wired the Author Class to Book class, even when we request for Author
        bean (dependent class) first, the dependency will be created first.
         */
        Author author01 = context.getBean(Author.class);
        Book book01 = context.getBean(Book.class);
        System.out.println(book01.getTitle());
        System.out.println(book01.toString());

        System.out.println(author01.toString());
        Author author02 = context.getBean("aliceOConnor",Author.class);
        System.out.println(author02.toString());


    }
}