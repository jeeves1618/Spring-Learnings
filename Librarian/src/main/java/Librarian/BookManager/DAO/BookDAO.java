package Librarian.BookManager.DAO;

import Librarian.BookManager.Entity.Book;

import java.util.List;

public interface BookDAO {

    public List<Book> getBooks();

    void saveBook(Book book);

    Book getBookbyID(int theID);

    void deleteBookById(int theID);
}
