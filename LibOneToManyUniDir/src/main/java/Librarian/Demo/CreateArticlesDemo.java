package Librarian.Demo;

import Librarian.BookManager.Entity.Articles;
import Librarian.BookManager.Entity.Authors;
import Librarian.BookManager.Entity.Book;
import Librarian.BookManager.Entity.BookDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class CreateArticlesDemo {

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
                .addAnnotatedClass(Articles.class)
                .buildSessionFactory();

        /*
        Create a session
         */
        Session session = sessionFactory.getCurrentSession();

        log.info("Creating a new student object. ");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

        try{
            BookDetail bookDetail = new BookDetail("amazon.in","Hardcover","978-1039484171");
            Authors author1 = new Authors("Tom","Jerry");
            Authors author2 = new Authors("Spider","Man");

            Book book = new Book("What was the fire for?","Child Prodigy","Multiple","Authors","Picador","2016-11-07",970.00,"INR",bookDetail);
            book.addAuthor(author1);
            book.addAuthor(author2);
            //log.info("Created a new student object: " +book.toString());

            session.beginTransaction();
            log.info("Persisting the book");
            session.save(book);
            session.save(author1);
            session.save(author2);

            Articles article1 = new Articles("Blog posts for the Author","https://cafehayek.com/");
            Articles article2 = new Articles("Russ's Podcasts","https://russroberts.info/econtalk/");

            author1.addArticles(article1);
            author2.addArticles(article2);

            session.save(article1);
            session.save(article2);

            session.getTransaction().commit();
            log.info("Record added and Commited.");
        }
        finally {
            session.close();
            sessionFactory.close();
        }
    }
}
