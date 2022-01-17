package net.myphenotype.SpringAOP.Service;

import lombok.extern.slf4j.Slf4j;
import net.myphenotype.SpringAOP.DAO.BookDao;
import net.myphenotype.SpringAOP.Entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public Iterable<Book> listBooks(){
        return bookDao.listBooks();
    }

    public String showBooks(){
        bookDao.showBooks();
        return "Test Method for Spring AOP";
    }
}
