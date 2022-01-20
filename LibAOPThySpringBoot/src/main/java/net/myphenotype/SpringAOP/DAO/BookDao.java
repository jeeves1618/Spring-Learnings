package net.myphenotype.SpringAOP.DAO;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.Entity.Book;
import net.myphenotype.SpringAOP.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class BookDao {
    @Autowired
    private BookRepository bookRepository;

    public BookDao(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> listBooks(){
        log.info("Listing books");
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {

        log.info("Saving Books");
        bookRepository.save(book);
    }

    public Book getBookbyID(int theID) {
        //Retrieve from DB using the primary key
        Optional<Book> theBook = bookRepository.findById(theID);
        Book book = theBook.get();
        log.info("Getting books by ID");
        return book;
    }

    public void deleteBookById(int theID){
        Optional<Book> theBook = bookRepository.findById(theID);
        Book book = theBook.get();
        if (book != null){
            bookRepository.delete(book);
            log.info("Deleting the entry for " + book.toString());
        } else {
            log.info("The book with the ID " + theID + " is not available");
        }
    }

    public List<Book> getBooksByPartialName(String theSearchName) {
        List<Book> books = bookRepository.findByBookTitleContaining(theSearchName);
        log.info("Listing books for the search criteria");
        return books;
    }
}