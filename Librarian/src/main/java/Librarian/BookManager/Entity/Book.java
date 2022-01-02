package Librarian.BookManager.Entity;

/*
Why am I using JPA annotations instead of Hibernate annotations here?

JPA (Java Persistence API) is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations. The Hibernate team recommends the use of JPA annotations as a best practice.
 */

import javax.persistence.*;

@Entity
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
    @Column(name = "email")
    private String contactEmail;

    public Book(){

    }

    public Book(String bookTitle, String bookGenre, String authorFirstName, String authorLastName, String publisherName, String dateOfPurchase, double costOfPurchase, String currencyCode, String contactEmail) {
        this.bookTitle = bookTitle;
        this.bookGenre = bookGenre;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisherName = publisherName;
        this.dateOfPurchase = dateOfPurchase;
        this.costOfPurchase = costOfPurchase;
        this.currencyCode = currencyCode;
        this.contactEmail = contactEmail;
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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /*
    This method is added only for displaying and debugging.
     */
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", publisherName='" + publisherName + '\'' +
                ", dateOfPurchase=" + dateOfPurchase +
                ", costOfPurchase=" + costOfPurchase +
                ", currencyCode='" + currencyCode + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                '}';
    }
}
