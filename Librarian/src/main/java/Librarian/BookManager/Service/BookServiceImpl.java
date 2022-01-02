package Librarian.BookManager.Service;

import Librarian.BookManager.DAO.BookDAO;
import Librarian.BookManager.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
public class BookServiceImpl implements BookService{
    /*
    Inject BookDao here
     */
    @Autowired
    private BookDAO bookDAO;

    @Override
    @Transactional
    public void deleteBookById(int theID) {
        bookDAO.deleteBookById(theID);
    }

    @Override
    @Transactional
    public List<Book> getBooks() {
        return bookDAO.getBooks();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookDAO.saveBook(book);
    }

    @Override
    @Transactional
    public Book getBookbyID(int theID) {
        return bookDAO.getBookbyID(theID);
    }
}
