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
@Table(name = "readings")
public class Readings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int Id;
    @Column(name = "date_of_reading")
    private String dateOfReading;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    @JsonIgnore
    private Book book;

    public Readings() {
    }

    public Readings(String dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(String dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Readings{" +
                "Id=" + Id +
                ", dateOfReading='" + dateOfReading + '\'' +
                ", book=" + book +
                '}';
    }
}
