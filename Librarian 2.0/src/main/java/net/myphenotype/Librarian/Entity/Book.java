package net.myphenotype.Librarian.Entity;
/*
Why am I using JPA annotations instead of Hibernate annotations here?

JPA (Java Persistence API) is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations. The Hibernate team recommends the use of JPA annotations as a best practice.
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Builder
@AllArgsConstructor
@Table(name="book")
public class Book {

    /*
    To define the DB attributes in the Entity class for mySQL, here is the data type mapping for reference.
    https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-type-conversions.html
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_title_additional_chars")
    private String bookTitleAdditionalChars;
    @Column(name = "book_genre")
    private String bookGenre;
    @Column(name = "author_first_name")
    private String authorFirstName;
    @Column(name = "author_last_name")
    private String authorLastName;
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "date_of_purchase")
    private String dateOfPurchase;
    @Column(name = "cost_of_acquisition")
    private double costOfPurchase;
    @Column(name = "currency_of_acquisition")
    private String currencyCode;
    @Column(name = "image_file_name")
    private String imageFileName;
    @Column(name = "read_status")
    private String readStatus;
    @Column(name = "date_of_reading")
    private String dateOfReading;
    @Column(name = "rating_of_usefulness", columnDefinition = "integer default 0")
    private Integer ratingOfUsefulness;
    @Column(name = "all_time_great_indicator", columnDefinition = "varchar(255) default 'No'")
    private String allTimeGreatIndicator;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "book_detail_id")
    private BookDetail bookDetail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Authors> authorsList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, orphanRemoval = true)
    private List<Readings> readingsList;

    @Transient
    private String authorsFirstName1;
    @Transient
    private String authorsLastName1;
    @Transient
    private String aboutAuthor1;
    @Transient
    private String authorsFirstName2;
    @Transient
    private String authorsLastName2;
    @Transient
    private String aboutAuthor2;
    @Transient
    private String authorsFirstName3;
    @Transient
    private String authorsLastName3;
    @Transient
    private String aboutAuthor3;
    @Transient
    private String authorsFirstName4;
    @Transient
    private String authorsLastName4;
    @Transient
    private String aboutAuthor4;

    public Book(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookTitleAdditionalChars() {
        return bookTitleAdditionalChars;
    }

    public void setBookTitleAdditionalChars(String bookTitleAdditionalChars) {
        this.bookTitleAdditionalChars = bookTitleAdditionalChars;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(String dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public double getCostOfPurchase() {
        return costOfPurchase;
    }

    public void setCostOfPurchase(double costOfPurchase) {
        this.costOfPurchase = costOfPurchase;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public Integer getRatingOfUsefulness() {
        return ratingOfUsefulness;
    }

    public void setRatingOfUsefulness(Integer ratingOfUsefulness) {
        this.ratingOfUsefulness = ratingOfUsefulness;
    }

    public BookDetail getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(BookDetail bookDetail) {
        this.bookDetail = bookDetail;
    }

    public List<Authors> getAuthorsList() {
        return authorsList;
    }

    public void setAuthorsList(List<Authors> authorsList) {
        this.authorsList = authorsList;
    }

    public String getAuthorsFirstName1() {
        return authorsFirstName1;
    }

    public void setAuthorsFirstName1(String authorsFirstName1) {
        this.authorsFirstName1 = authorsFirstName1;
    }

    public String getAuthorsLastName1() {
        return authorsLastName1;
    }

    public void setAuthorsLastName1(String authorsLastName1) {
        this.authorsLastName1 = authorsLastName1;
    }

    public String getAboutAuthor1() {
        return aboutAuthor1;
    }

    public void setAboutAuthor1(String aboutAuthor1) {
        this.aboutAuthor1 = aboutAuthor1;
    }

    public String getAuthorsFirstName2() {
        return authorsFirstName2;
    }

    public void setAuthorsFirstName2(String authorsFirstName2) {
        this.authorsFirstName2 = authorsFirstName2;
    }

    public String getAuthorsLastName2() {
        return authorsLastName2;
    }

    public void setAuthorsLastName2(String authorsLastName2) {
        this.authorsLastName2 = authorsLastName2;
    }

    public String getAboutAuthor2() {
        return aboutAuthor2;
    }

    public void setAboutAuthor2(String aboutAuthor2) {
        this.aboutAuthor2 = aboutAuthor2;
    }

    public String getAuthorsFirstName3() {
        return authorsFirstName3;
    }

    public void setAuthorsFirstName3(String authorsFirstName3) {
        this.authorsFirstName3 = authorsFirstName3;
    }

    public String getAuthorsLastName3() {
        return authorsLastName3;
    }

    public void setAuthorsLastName3(String authorsLastName3) {
        this.authorsLastName3 = authorsLastName3;
    }

    public String getAboutAuthor3() {
        return aboutAuthor3;
    }

    public void setAboutAuthor3(String aboutAuthor3) {
        this.aboutAuthor3 = aboutAuthor3;
    }

    public String getAuthorsFirstName4() {
        return authorsFirstName4;
    }

    public void setAuthorsFirstName4(String authorsFirstName4) {
        this.authorsFirstName4 = authorsFirstName4;
    }

    public String getAuthorsLastName4() {
        return authorsLastName4;
    }

    public void setAuthorsLastName4(String authorsLastName4) {
        this.authorsLastName4 = authorsLastName4;
    }

    public String getAboutAuthor4() {
        return aboutAuthor4;
    }

    public void setAboutAuthor4(String aboutAuthor4) {
        this.aboutAuthor4 = aboutAuthor4;
    }

    public String getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(String readStatus) {
        this.readStatus = readStatus;
    }

    public String getDateOfReading() {
        return dateOfReading;
    }

    public void setDateOfReading(String dateOfReading) {
        this.dateOfReading = dateOfReading;
    }

    public String getAllTimeGreatIndicator() {
        return allTimeGreatIndicator;
    }

    public void setAllTimeGreatIndicator(String allTimeGreatIndicator) {
        this.allTimeGreatIndicator = allTimeGreatIndicator;
    }

    public List<Readings> getReadingsList() {
        return readingsList;
    }

    public void setReadingsList(List<Readings> readingsList) {
        this.readingsList = readingsList;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", dateOfPurchase='" + dateOfPurchase + '\'' +
                ", costOfPurchase=" + costOfPurchase +
                ", currencyCode='" + currencyCode + '\'' +
                '}';
    }

    public void addAuthor(Authors authors){
        if(authorsList == null){
            authorsList = new ArrayList<>();
        }

        authorsList.add(authors);
        authors.setBook(this);
    }

    public void addReading(Readings readings){
        if(readingsList == null){
            readingsList = new ArrayList<>();
        }

        readingsList.add(readings);
        readings.setBook(this);
    }
}
