package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Slf4j
@Component
public class BookLoverHistoryBuffPersona implements BookLover{

    private String bookLoverName;
    private String bookLoverEmail;

    @NotNull()
    @Size(min=5, message = "is required")
    public String getBookLoverName() {
        return bookLoverName;
    }

    public void setBookLoverName(String bookLoverName) {
        this.bookLoverName = bookLoverName;
    }

    public String getBookLoverEmail() {
        return bookLoverEmail;
    }

    public void setBookLoverEmail(String bookLoverEmail) {
        this.bookLoverEmail = bookLoverEmail;
    }
}
