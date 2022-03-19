package net.myphenotype.Librarian.Configuration;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.myphenotype.Librarian.Entity.Authors;
import net.myphenotype.Librarian.Entity.Book;
import net.myphenotype.Librarian.Entity.BookDetail;
import net.myphenotype.Librarian.Repository.AuthorRepository;
import net.myphenotype.Librarian.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
@Profile("madeup")
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final Faker faker = new Faker();

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
        log.info("==========Database is beginning to load ===================");
        Set<Book> books = new HashSet<>();
        IntStream.range(0,bookCount).forEach((index) -> {
            LocalDate orderDate = faker.date().past(4, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Book book = Book.builder()
                    .bookTitle(faker.book().title())
                    .bookGenre(faker.book().genre())
                    .authorFirstName(faker.name().firstName())
                    .authorLastName(faker.name().lastName())
                    .publisherName(faker.book().publisher())
                    .dateOfPurchase(String.valueOf(orderDate))
                    .costOfPurchase(faker.number().randomDouble(2,100,10000))
                    .currencyCode(faker.currency().code())
                    .build();
            BookDetail bookDetail = BookDetail.builder()
                    .shoppingChannel(faker.company().name())
                    .typeOfBinding(faker.book().toString())
                    .isbNumber(faker.number().digit())
                    .build();
            bookDetail.setBook(book);

            IntStream.range(0, faker.number().numberBetween(1,authorCount))
                    .forEach(value -> {
                        Authors authors = Authors.builder()
                                .authorsFirstName(faker.name().firstName())
                                .authorsLastName(faker.name().lastName())
                                .build();
                        authors.setBook(book);
                        book.addAuthor(authors);
                    });
            books.add(book);
        });
        this.bookRepository.saveAll(books);
        log.info("========== Data load is completed ===================");
    }
}
