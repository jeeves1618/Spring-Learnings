package Librarian.BookManager.Entity;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "book_author")
@Slf4j
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "author_first_name")
    private String authorsFirstName;
    @Column(name = "author_last_name")
    private String authorsLastName;
    @Column(name = "about_author")
    private String aboutAuthor;
    //@Column(name = "book_id",insertable = false,updatable = false)
    //private int bookID;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "book_id")
    private Book book;

    public BookAuthor() {
    }

    public static Logger getLog() {
        return log;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorsFirstName() {
        return authorsFirstName;
    }

    public void setAuthorsFirstName(String authorsFirstName) {
        this.authorsFirstName = authorsFirstName;
    }

    public String getAuthorsLastName() {
        return authorsLastName;
    }

    public void setAuthorsLastName(String authorsLastName) {
        this.authorsLastName = authorsLastName;
    }

    public String getAboutAuthor() {
        return aboutAuthor;
    }

    public void setAboutAuthor(String aboutAuthor) {
        this.aboutAuthor = aboutAuthor;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookAuthor{" +
                "id=" + id +
                ", authorsFirstName='" + authorsFirstName + '\'' +
                ", authorsLastName='" + authorsLastName + '\'' +
                ", aboutAuthor='" + aboutAuthor + '\'' +
                '}';
    }
}
