package net.myphenotype.repository;

import net.myphenotype.entity.BookDetail;
import org.springframework.data.repository.CrudRepository;

public interface BookDetailRepository extends CrudRepository<BookDetail, Integer> {
}
