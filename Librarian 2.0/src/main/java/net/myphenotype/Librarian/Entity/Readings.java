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
    @Column(name = "rating_of_usefulness", columnDefinition = "integer default 0")
    private Integer ratingOfUsefulness;
    @Column(name = "all_time_great_indicator", columnDefinition = "varchar(255) default 'No'")
    private String allTimeGreatIndicator;
    @Column(name = "reading_notes_url")
    private String readingNotesUrl;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    @JsonIgnore
    private Book book;

    public Readings() {
    }

    public Readings(String dateOfReading, Integer ratingOfUsefulness, String allTimeGreatIndicator, String readingNotesUrl) {
        this.dateOfReading = dateOfReading;
        this.ratingOfUsefulness = ratingOfUsefulness;
        this.allTimeGreatIndicator = allTimeGreatIndicator;
        this.readingNotesUrl = readingNotesUrl;
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

    public Integer getRatingOfUsefulness() {
        return ratingOfUsefulness;
    }

    public void setRatingOfUsefulness(Integer ratingOfUsefulness) {
        this.ratingOfUsefulness = ratingOfUsefulness;
    }

    public String getAllTimeGreatIndicator() {
        return allTimeGreatIndicator;
    }

    public void setAllTimeGreatIndicator(String allTimeGreatIndicator) {
        this.allTimeGreatIndicator = allTimeGreatIndicator;
    }

    public String getReadingNotesUrl() {
        return readingNotesUrl;
    }

    public void setReadingNotesUrl(String readingNotesUrl) {
        this.readingNotesUrl = readingNotesUrl;
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
                ", ratingOfUsefulness=" + ratingOfUsefulness +
                ", allTimeGreatIndicator='" + allTimeGreatIndicator + '\'' +
                ", readingNotesUrl='" + readingNotesUrl + '\'' +
                ", book=" + book +
                '}';
    }
}
