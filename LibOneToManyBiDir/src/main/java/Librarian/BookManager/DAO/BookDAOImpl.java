package Librarian.BookManager.DAO;

import Librarian.BookManager.Entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Repository
public class BookDAOImpl implements BookDAO{

    /*
    Inject the Session Factory
     */
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Book> getBooks() {
        /*
        This function in the DAO is going to do 4 things.
        1. Get the current Hibernate Session
        2. Create a query
        3. Execute the query and receive the resultant rows
        4. Return the Results.
         */
        /*

        This is how you create session factory and link it to hibernate configuration xml
        if you don't want to do it through XML.

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Book.class)
                .addAnnotatedClass(BookDetail.class)
                .buildSessionFactory();
         */
        Session session = sessionFactory.getCurrentSession();

        Query<Book> query = session.createQuery("from Book",Book.class);
        /*
        The above query can be changed to order by authorLastName or any other fields and
        can be added to a new method, say getBooksByLastName. It can be invoked while
        clicking a header link from the book summary page.
         */

        List<Book> bookList = query.getResultList();

        return bookList;
    }

    @Override
    public void saveBook(Book book) {
        //Get the Hibernate Session
        Session session = sessionFactory.getCurrentSession();

        //BookDetail bookDetail = new BookDetail();
        //Save the book to the DB. Session.save will insert where as session.update will update.
        //if you are not sure, use saveorupdate. Hibernate will decide based on whether the primary key is null or not
        //merge is not working for bidirectional one to one mapping
        session.saveOrUpdate(book);
    }

    @Override
    public Book getBookbyID(int theID) {
        //Get the Hibernate Session
        Session session = sessionFactory.getCurrentSession();

        //Retrieve from DB using the primary key
        Book theBook = session.get(Book.class, theID);
        return theBook;
    }

    @Override
    public void deleteBookById(int theID) {
        //Get the Hibernate Session
        Session session = sessionFactory.getCurrentSession();

        //Delete from DB using the primary key
        Book tempBook = session.get(Book.class, theID);
        if (tempBook != null){
            System.out.println("Deleting " + tempBook);
            session.delete(tempBook);
        } else {
            log.info("The book does not exist for the given the ID");
        }
        /*
        Query deleteQuery = session.createQuery("delete from Book where id =:bookID");
        deleteQuery.setParameter("bookID", theID);
        deleteQuery.executeUpdate();
         */
    }

    @Override
    public List<Book> getBooksByPartialName(String theSearchName) {
        Session session = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =session.createQuery("from Book where lower(bookTitle) like :theName", Book.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =session.createQuery("from Customer", Book.class);
        }

        // execute query and get result list
        List<Book> bookList = theQuery.getResultList();

        // return the results
        return bookList;
    }
}
