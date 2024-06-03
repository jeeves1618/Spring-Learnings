package net.myphenotype.Librarian.DAO;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Entity.Authors;
import net.myphenotype.Librarian.Entity.Book;
import net.myphenotype.Librarian.Entity.Topic;
import net.myphenotype.Librarian.Repository.AuthorRepository;
import net.myphenotype.Librarian.Repository.BookRepository;
import net.myphenotype.Librarian.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class BookDao {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private TopicRepository topicRepository;

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
        try {
            Optional<Book> theBook = bookRepository.findById(theID);
            log.info("Getting books by ID");
            Book book = theBook.get();
            return book;
        } catch(Exception e){
            log.info(e.getMessage());
            Book book = null;
            return book;
        }
    }

    public List<Authors> getAuthorsByBookId(int theID) {
        //Retrieve from DB using the primary key
        List<Authors> authors = authorRepository.findByBookId(theID);

        log.info("Getting authors by Book ID");
        return authors;
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

    public List<Book> getAllTimeGreatsByPartialName(String theSearchName, String allTimeGreatIndicator) {
        List<Book> books = bookRepository.findByBookTitleContainingAndAllTimeGreatIndicator(theSearchName, allTimeGreatIndicator);
        log.info("Listing all time great books for the search criteria");
        return books;
    }

    public List<Topic> findCountByTopics(){
        return topicRepository.findAllByOrderByBookCountDesc();
    }

    public void saveAll(List<Topic> topicList){
        topicRepository.saveAll(topicList);
    }

    public List<Book> listBookByTopics(String genre){
        return bookRepository.findByBookGenre(genre);
    }

    public void saveTopic(Topic topic){
        topicRepository.save(topic);
    }

    public List<Topic> findTopicByGenre(String genre){
        return topicRepository.findByBookGenre(genre);
    }

}
