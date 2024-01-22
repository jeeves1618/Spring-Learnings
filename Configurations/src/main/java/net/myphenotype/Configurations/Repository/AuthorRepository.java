package net.myphenotype.Configurations.Repository;

import net.myphenotype.Configurations.Entity.Authors;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    List<Authors> findByBookId(int bookId);
}
