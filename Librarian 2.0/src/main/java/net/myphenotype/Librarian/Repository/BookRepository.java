package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByBookTitleContaining(String bookTitle);
}
