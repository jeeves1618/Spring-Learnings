package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Slf4j
@Component
public class BookLoverHistoryBuffPersona implements BookLover{
    @NotNull(message = "bookLoverName is required")
    @Size(min=5, message = "bookLoverName is required")
    @Pattern(regexp = "^[\\p{L} .'-]+$", message = "Special Characters are not allowed for name")
    private String bookLoverName;
    private String bookLoverEmail;
    @Min(value=18, message="Minors require parental approval to have their details revealed")
    @Max(value=120, message="Please enter a realistic age!")
    @NotNull(message = "Age is required")
    private Integer bookLoverAge;

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

    public Integer getBookLoverAge() {
        return bookLoverAge;
    }

    public void setBookLoverAge(Integer bookLoverAge) {
        this.bookLoverAge = bookLoverAge;
    }
}
