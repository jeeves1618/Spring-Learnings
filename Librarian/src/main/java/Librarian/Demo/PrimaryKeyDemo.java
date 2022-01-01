package Librarian.Demo;

import Librarian.BookManager.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@Slf4j
public class PrimaryKeyDemo {

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
            Book book1 = context.getBean("bookEntity1",Book.class);
            log.info("Created a new book object for " +book1.getBookTitle());
            Book book2 = context.getBean("bookEntity2",Book.class);
            log.info("Created a new book object for " +book2.getBookTitle());
            Book book3 = context.getBean("bookEntity3",Book.class);
            log.info("Created a new book object for " +book3.getBookTitle());

            session.beginTransaction();
            log.info("Persisting the book");
            session.save(book1);
            session.save(book2);
            session.save(book3);
            session.getTransaction().commit();
            log.info("Records added and Commited.");
            session.close();
        }
        finally {
            sessionFactory.close();
        }

    }
}
