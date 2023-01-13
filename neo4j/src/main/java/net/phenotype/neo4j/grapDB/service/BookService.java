package net.phenotype.neo4j.grapDB.service;

import net.phenotype.neo4j.grapDB.entity.Book;
import net.phenotype.neo4j.grapDB.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public String deleteBook(Long id){
        Book book = bookRepository.findById(id).get();
        bookRepository.deleteById(id);
        return "The book, " + book.getTitle() + ", published by " + book.getPublisher() + " is successfully deleted";
    }

    public List<Book> getBooksByAuthor(String firstName, String lastName){
        return bookRepository.getBooksByAuthor(firstName, lastName);
    }

    public List<Book> findAllbyPage(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookRepository.findAll(pageable).getContent();
    }

    public List<Book> findAllbyPageSorted(int pageNo, int pageSize){
        //Sorting
        Sort sort = Sort.by(Sort.Direction.ASC,"title");
        List<Book> bookList = bookRepository.findAll(sort);
        System.out.println(bookList);
        //Sorting with Pagenation
        Pageable pageable = PageRequest.of(pageNo, pageSize,Sort.by(Sort.Direction.ASC,"title"));
        return bookRepository.findAll(pageable).getContent();
    }

    public List<Book> findAllbyTitleLike(String partialTitle){
        return bookRepository.findByTitleLike(partialTitle);
    }

    public List<Book> findAllbyTitleStartsWith(String partialTitle){
        return bookRepository.findByTitleStartsWith(partialTitle);
    }

    public List<Book> findAllbyEndsWith(String partialTitle){
        return bookRepository.findByTitleEndsWith(partialTitle);
    }
}
