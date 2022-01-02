package Librarian.BookManager.DAO;

import Librarian.BookManager.Entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Component
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

        //Save the book to the DB. Session.save will insert where as session.update will update.
        //if you are not sure, use saveorupdate. Hibernate will decide based on whether the primary key is null or not
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
        Query deleteQuery = session.createQuery("delete from Book where id =:bookID");
        deleteQuery.setParameter("bookID", theID);
        deleteQuery.executeUpdate();
    }
}
