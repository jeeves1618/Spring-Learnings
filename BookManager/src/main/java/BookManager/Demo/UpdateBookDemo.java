package BookManager.Demo;

import BookManager.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class UpdateBookDemo {
    /*Spring does not allow to inject value into static variables.*/
    @Value("${book.ID.to.select.from.table}")
    private static int bookID;
    public static void main(String[] args) {

        System.out.println(bookID);

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
            bookID = 2;
            log.info("Getting book from the table for ID : " + bookID);
            Book myBook = session.get(Book.class,bookID);
            log.info("The book retrieved is : " + myBook.toString());
            /*
            Updating a specific row on primary key. It does not even need create query
             */
            myBook.setContactEmail("stillDontknow@nothing.com");

            /*
            Bulk update for a condition
             */
            session.createQuery("update Book set contactEmail = 'whyisthisfieldhere@wasteofspace.com' " +
                    "where contactEmail = 'stillDontknow@nothing.com'").executeUpdate();
            session.getTransaction().commit();
            log.info("Record Commited.");
        }
        finally {
            sessionFactory.close();
        }
    }
}
