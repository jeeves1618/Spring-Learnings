package Librarian.BookManager.DAO;

import Librarian.BookManager.Entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

        Session session = sessionFactory.getCurrentSession();

        Query<Book> query = session.createQuery("from Book",Book.class);

        List<Book> bookList = query.getResultList();

        return bookList;
    }
}
