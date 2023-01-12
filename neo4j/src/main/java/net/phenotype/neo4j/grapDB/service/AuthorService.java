package net.phenotype.neo4j.grapDB.service;

import net.phenotype.neo4j.grapDB.domain.CreateAuthorRequest;
import net.phenotype.neo4j.grapDB.domain.CreateBookRequest;
import net.phenotype.neo4j.grapDB.entity.Author;
import net.phenotype.neo4j.grapDB.entity.Book;
import net.phenotype.neo4j.grapDB.entity.HasWrittenRelation;
import net.phenotype.neo4j.grapDB.entity.Location;
import net.phenotype.neo4j.grapDB.repo.AuthorRepository;
import net.phenotype.neo4j.grapDB.repo.BookRepository;
import net.phenotype.neo4j.grapDB.repo.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    LocationRepository locationRepository;

    public Author createAuthor(CreateAuthorRequest createAuthorRequest){
        System.out.println(createAuthorRequest.toString());
        Location location = new Location();
        location.setCity(createAuthorRequest.getCreateLocationRequest().getCity());
        location.setCountry(createAuthorRequest.getCreateLocationRequest().getCountry());

        List<HasWrittenRelation> hasWrittenRelationList = new ArrayList<>();

        locationRepository.save(location);
        System.out.println("Written the location : " + location);
        List<Book> bookList = new ArrayList<>();
        Book book = null;
        if(createAuthorRequest.getBookList() != null) {
            for (CreateBookRequest temp : createAuthorRequest.getBookList()) {
                book = new Book();
                book.setTitle(temp.getTitle());
                book.setPublisher(temp.getPublisher());
                book.setPriceOfBook(temp.getPriceOfBook());
                bookList.add(book);

                HasWrittenRelation hasWrittenRelation = new HasWrittenRelation();
                hasWrittenRelation.setPages(temp.getPages());
                hasWrittenRelation.setBook(book);
                hasWrittenRelationList.add(hasWrittenRelation);
            }
            bookRepository.saveAll(bookList);
        }

        Author author = new Author();
        author.setFirstName(createAuthorRequest.getFirstName());
        author.setLastName(createAuthorRequest.getLastName());
        author.setBorn(createAuthorRequest.getBorn());
        author.setLocation(location);
        author.setHasWrittenList(hasWrittenRelationList);
        authorRepository.save(author);

        return author;
    }
}
