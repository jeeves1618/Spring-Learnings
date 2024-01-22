package net.myphenotype.Configurations.Domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@ToString
public class TopicSummary {
    private String bookGenre;
    private long bookCount;

    public TopicSummary(String bookGenre, long bookCount) {
        this.bookGenre = bookGenre;
        this.bookCount = bookCount;
    }
}
