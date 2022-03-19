package net.myphenotype.Librarian.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Builder
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;
    @Column(name="authors_first_name")
    private String authorsFirstName;
    @Column(name="authors_last_name")
    private String authorsLastName;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    @JsonIgnore
    private Book book;

    public Authors() {
    }

    public Authors(String authorsFirstName, String authorsLastName) {
        this.authorsFirstName = authorsFirstName;
        this.authorsLastName = authorsLastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "Id=" + Id +
                ", authorsFirstName='" + authorsFirstName + '\'' +
                ", authorsLastName='" + authorsLastName + '\'' +
                '}';
    }
}
