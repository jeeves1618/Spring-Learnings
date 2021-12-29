package Book;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
@Slf4j
@Component
public class BookDetailGenre implements BookDetailInterface{

    private String titleOfTheBook;
    private String firstName;
    private String lastName;
    private String bookGenre;
    private String typeOfBinding;
    private String[] purposesOfReading;

    public String[] getPurposesOfReading() {
        return purposesOfReading;
    }

    public void setPurposesOfReading(String[] purposesOfReading) {
        log.info("Number of purposes: " + purposesOfReading.length);
        this.purposesOfReading = purposesOfReading;
    }

    public String getTypeOfBinding() {
        return typeOfBinding;
    }

    public void setTypeOfBinding(String typeOfBinding) {
        log.info("Type of binding: " + typeOfBinding);
        this.typeOfBinding = typeOfBinding;
    }
    /*
    How to populate radiobuttons
    with items from Java class like we did with selectlist?

    1. Add a new field private LinkedHashMap<String, String> typesOfBinding;
    2.// populate favorite language options
        typesOfBinding= new LinkedHashMap<>();
        // parameter order: value, display label
        //
        typesOfBinding.put("Hard Cover", "Hard Cover");
        typesOfBinding.put("Soft Cover", "Soft Cover");
        typesOfBinding.put("Leather Bound", "Leather Bound");
        typesOfBinding.put("Library Binding", "Library Binding");
     3. In the HTML, access the typesOfBinding as below.
     <form:radiobuttons path="typeOfBinding" items="${book.typesOfBinding}"
     */
    private LinkedHashMap<String, String > genreOptions;

    public LinkedHashMap<String, String> getGenreOptions() {
        return genreOptions;
    }

    public BookDetailGenre() {

        genreOptions = new LinkedHashMap<>();

        genreOptions.put("History","History");
        genreOptions.put("Politics","Politics");
        genreOptions.put("Science","Science");
        genreOptions.put("Travel","Travel");
        genreOptions.put("Business","Business");
        genreOptions.put("Fiction","Fiction");
        genreOptions.put("Sports","Sports");
        genreOptions.put("Art","Art");
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
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
