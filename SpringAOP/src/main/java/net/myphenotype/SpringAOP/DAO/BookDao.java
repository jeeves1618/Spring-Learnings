package net.myphenotype.SpringAOP.DAO;

import net.myphenotype.SpringAOP.Entity.Book;
import net.myphenotype.SpringAOP.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDao {
    @Autowired
    private BookRepository bookRepository;

    public void showBooks(){

    }

    public Iterable<Book> listBooks(){
        return bookRepository.findAll();
    }
}
