package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Domain.TopicSummary;
import net.myphenotype.Librarian.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByBookTitleContaining(String bookTitle);

    @Query("select new net.myphenotype.Librarian.Domain.TopicSummary(bookGenre, count(*)) from Book group by bookGenre order by count(*) desc ")
    List<TopicSummary> findCountByTopics();

    List<Book> findByBookGenre(String genre);
}
