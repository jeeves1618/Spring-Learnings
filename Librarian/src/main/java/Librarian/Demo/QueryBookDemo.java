package Librarian.Demo;

import Librarian.BookManager.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

@Slf4j
public class QueryBookDemo {
    public static void main(String[] args) {
        /*
        If you are using Hibernate 5.2 or higher, then the Query list() method has been deprecated. In your code
        you should make the following update:
        Replace
        session.createQuery("from Student").list()
        With
        session.createQuery("from Student").getResultList()
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
                .buildSessionFactory();

        /*
        Create a session
         */
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();

            //Querying all the records in the Book table. Equivalent to SELECT ALL in SQL
            // The 'from Book' string should be the actual class name and
            //not the label specified in @Table of the entity class.
            log.info("Listing all books ----------------------------");
            List<Book> bookList = session.createQuery("from Book").getResultList();
            displayBooks(bookList);

            //The column name in the Where should be the class name and not the label.
            log.info("Listing select books -------------------------");
            bookList = session.createQuery("from Book B where B.authorLastName = 'Wodehouse'").getResultList();
            displayBooks(bookList);


            log.info("Listing books with last name as frakl or book genre is History--------");
            bookList = session.createQuery("from Book B where B.authorLastName = 'Frankl' or B.bookGenre = 'History'").getResultList();
            displayBooks(bookList);
            session.getTransaction().commit();
            log.info("");
        }
        finally {
            sessionFactory.close();
        }
    }
    public static void displayBooks(List<Book> bookList){
        //Listing the retrieved Books
        for(Book iteratorBook: bookList){
            log.info(iteratorBook.toString());
        }
    }
}
