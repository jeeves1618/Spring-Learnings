package net.myphenotype.SpringAOP.Repository;

import net.myphenotype.SpringAOP.Entity.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer>{
}
