package Book;

import org.springframework.stereotype.Component;

@Component
public class BookDetail implements BookDetailInterface{

    private String titleOfTheBook;
    private String firstName;
    private String lastName;

    public BookDetail() {
    }

    public String getTitleOfTheBook() {
        return titleOfTheBook;
    }

    public void setTitleOfTheBook(String titleOfTheBook) {
        this.titleOfTheBook = titleOfTheBook;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
