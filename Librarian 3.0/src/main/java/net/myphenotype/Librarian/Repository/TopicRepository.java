package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    List<Topic> findByBookGenre(String genre);

    List<Topic> findAllByOrderByBookCountDesc();
}
