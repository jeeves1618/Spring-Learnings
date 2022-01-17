package net.myphenotype.SpringAOP.DAO;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.Entity.Book;
import net.myphenotype.SpringAOP.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BookDao {
    @Autowired
    private BookRepository bookRepository;

    public void showBooks(){
        log.info("Test code for Spring AOP");
    }

    public Iterable<Book> listBooks(){
        return bookRepository.findAll();
    }
}
