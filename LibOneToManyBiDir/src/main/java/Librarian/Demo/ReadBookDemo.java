package Librarian.Demo;

import Librarian.BookManager.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class ReadBookDemo {
    public static void main(String[] args) {
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
                .buildSessionFactory();

        /*
        Create a session
         */
        Session session = sessionFactory.getCurrentSession();

        log.info("Creating a new student object. ");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(APPLICATION_CONTEXT_XML);

        try{
            Book book = context.getBean("bookEntity",Book.class);
            log.info("Created a new student object: " + book.toString());

            session.beginTransaction();
            log.info("Persisting the book");
            session.save(book);
            session.getTransaction().commit();
            log.info("Record added and Commited.");

            //Now get a new session and begin a new transaction for reading.
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            log.info("Getting the book with the ID " + book.getId());
            Book mybook = session.get(Book.class, book.getId());

            log.info("Here is the retrieved row: " + mybook.toString());
        }
        finally {
            sessionFactory.close();
        }
    }
}
