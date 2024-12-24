package net.myphenotype.Librarian.Configuration;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Repository.BookRepository;
import net.myphenotype.Librarian.Repository.ReadingsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("madeup")
public class ReadingsConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final BookRepository bookRepository;
    private final ReadingsRepository readingsRepository;
    private final Faker faker = new Faker();

    BindFinder bindFinder;

    @Value("${lib.bookcount}")
    private int bookCount;

    @Value("${lib.authorcount}")
    private int authorCount;

    /**
     * Handle an application event.
     *
     * @param event the event to respond to
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("========== Data load is beginning. Reading de" + bookCount + " books will be loaded. ===================");

        log.info("========== Data load is completed for " + bookCount + " books ===================");
    }
}
