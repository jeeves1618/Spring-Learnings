package net.myphenotype.Librarian.Repository;

import net.myphenotype.Librarian.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

}
