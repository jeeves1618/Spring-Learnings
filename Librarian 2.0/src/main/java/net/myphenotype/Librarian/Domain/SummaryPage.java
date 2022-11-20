package net.myphenotype.Librarian.Domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Data
@Component
public class SummaryPage {

    private String bookGenre;
    private long bookCount;
    private String formattedAmount;
}
