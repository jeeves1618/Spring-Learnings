package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    List<Authors> findByBookId(int bookId);
}
