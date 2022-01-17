package Librarian.Demo;

import Librarian.BookManager.Entity.Authors;
import Librarian.BookManager.Entity.Book;
import Librarian.BookManager.Entity.BookDetail;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@Slf4j
public class DeleteCourseDemo {

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

        try{
            int bookID = 6;
            session.beginTransaction();
            //Retrieve the book that needs to be deleted.
            Book myUnwantedBook = session.get(Book.class, bookID);
            // Display the unwanted Book.
            log.info("The book to be deleted is :" + myUnwantedBook.toString());

            //Delete the book
            session.delete(myUnwantedBook);

            //Commit the Delete
            session.getTransaction().commit();

            log.info("Record deleted and Commited.");
        }
        finally {
            sessionFactory.close();
        }
    }
}
