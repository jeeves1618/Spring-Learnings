package net.myphenotype.RESTWebClient.Domain;
/*
Why am I using JPA annotations instead of Hibernate annotations here?

JPA (Java Persistence API) is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations. The Hibernate team recommends the use of JPA annotations as a best practice.
 */

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Book {

    private int id;
    private String bookTitle;
    private String bookGenre;
    private String authorFirstName;
    private String authorLastName;
    private String publisherName;
    private String dateOfPurchase;
    private double costOfPurchase;
    private String currencyCode;
    private BookDetail bookDetail;
    private List<Authors> authorsList;

    private String authorsFirstName1;
    private String authorsLastName1;
    private String aboutAuthor1;
    private String authorsFirstName2;
    private String authorsLastName2;
    private String aboutAuthor2;
    private String authorsFirstName3;
    private String authorsLastName3;
    private String aboutAuthor3;
    private String authorsFirstName4;
    private String authorsLastName4;
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
    }
}
