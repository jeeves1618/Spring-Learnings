package net.myphenotype.repository;

import net.myphenotype.entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer>{
}
