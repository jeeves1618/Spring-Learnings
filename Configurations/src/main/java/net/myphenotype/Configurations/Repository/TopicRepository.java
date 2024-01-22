package net.myphenotype.Configurations.Repository;

import net.myphenotype.Configurations.Entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Integer> {

    List<Topic> findByBookGenre(String genre);

    List<Topic> findAllByOrderByBookCountDesc();
}
