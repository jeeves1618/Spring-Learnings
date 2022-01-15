package Librarian.Demo;

import Librarian.BookManager.Entity.Authors;
import Librarian.BookManager.Entity.Book;
import Librarian.BookManager.Entity.BookDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreateBookDemo {

    public static void main(String[] args) {

        /*Create a Session Factory
          SessionFactory is an interface. SessionFactory can be created by providing Configuration object,
          which will contain all DB related property details pulled from either hibernate.cfg.xml file or
          hibernate.properties file. SessionFactory is a factory for Session objects.

          We can create one SessionFactory implementation per database in any application. If your application
          is referring to multiple databases, then you need to create one SessionFactory per database. The
          SessionFactory is a heavyweight object; it is usually created during application start up and kept
          for later use. The SessionFactory is a thread safe object and used by all the threads of an application.
         */
        String APPLICATION_CONTEXT_XML="applicationContext.xml";
        SessionFactory sessionFactory = new Configuration()
                /*
                One of the most required configuration file in Hibernate is hibernate.cfg.xml file.
                By default, it is placed under src/main/resource folder. hibernate.cfg.xml file
                contains database related configurations and session related configurations.

                If the file name is hibernate.cfg.xml, it need not be explicitly stated here.
                I am giving the name for the sake of clarity.
                 */
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookDetail.class)
                .addAnnotatedClass(Authors.class)
                .buildSessionFactory();

        /*
        Create a session
         */
        Session session = sessionFactory.getCurrentSession();

        log.info("Creating a new student object. ");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

        try{
            BookDetail bookDetail = new BookDetail("amazon.in","Hardcover","978-1039484171");
            Authors authors = new Authors("Tom","Jerry");
            List<Authors> authorsList = new ArrayList<>();
            authorsList.add(authors);
            Book book = new Book("What was the fire for?","Child Prodigy","Tom","Jerry","Picador","2016-11-07",970.00,"INR",bookDetail);
            log.info("Created a new student object: " +book.toString());

            session.beginTransaction();
            log.info("Persisting the book");
            session.save(book);
            session.getTransaction().commit();
            log.info("Record added and Commited.");
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
