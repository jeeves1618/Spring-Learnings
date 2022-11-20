package net.myphenotype.Librarian.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Domain.TopicSummary;
import net.myphenotype.Librarian.Entity.Topic;
import net.myphenotype.Librarian.Repository.BookRepository;
import net.myphenotype.Librarian.Repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("madeup")
public class TopicTableLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final BookRepository bookRepository;
    private final TopicRepository topicRepository;

    @Autowired
    Topic topic;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("========== Data load is beginning. topics will be loaded. ===================");
        topicRepository.deleteAll();
        List<TopicSummary> topicSummaryList = bookRepository.findCountByTopics();
        for(TopicSummary topicSummary: topicSummaryList){
            topic.setBookGenre(topicSummary.getBookGenre());
            topic.setBookCount(topicSummary.getBookCount());
            System.out.println("The Book Genre is : " + topicSummary.getBookGenre());
            switch (topicSummary.getBookGenre()) {
                case "History":
                    topic.setImageFileName("/img/topic/topic-page-01.jpg");
                default:
                    topic.setImageFileName("/img/topic/topic-page-00.jpg");
            }
            topicRepository.save(topic);
            topic = new Topic();
        }
        log.info("========== Data load is completed for Topics ===================");
    }
}
