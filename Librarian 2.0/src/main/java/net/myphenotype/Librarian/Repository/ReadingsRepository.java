package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.Readings;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingsRepository extends JpaRepository<Readings, Integer> {
    List<Readings> findByBookId(int bookId);
}
