package Book;

import org.springframework.stereotype.Component;

@Component
public class BookDetail implements BookDetailInterface{

    private String titleOfTheBook;
    private String firstName;
    private String lastName;
    private String bookGenre;
    private String typeOfBinding;
    private String[] purposesOfReading;

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getTypeOfBinding() {
        return typeOfBinding;
    }

    public void setTypeOfBinding(String typeOfBinding) {
        this.typeOfBinding = typeOfBinding;
    }

    @Override
    public String[] getPurposesOfReading() {
        return new String[0];
    }

    @Override
    public void setPurposesOfReading(String[] purposesOfReading) {
        this.purposesOfReading = purposesOfReading;
    }

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
