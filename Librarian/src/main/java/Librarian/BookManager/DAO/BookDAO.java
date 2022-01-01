package Librarian.BookManager.DAO;

import Librarian.BookManager.Entity.Book;

import java.util.List;

public interface BookDAO {

    public List<Book> getBooks();
}
