package net.myphenotype.Librarian.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Builder
@AllArgsConstructor
@Entity
@Table(name = "book_detail")
@Slf4j
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "shopping_channel")
    private String shoppingChannel;
    @Column(name = "type_of_binding")
    private String typeOfBinding;
    @Column(name = "isbn")
    private String isbNumber;
    @Column(name = "shopping_url")
    private String shoppingUrl;
    @JsonIgnore
    @OneToOne(mappedBy = "bookDetail", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Book book;

    public BookDetail() {
    }

    public BookDetail(String shoppingChannel, String typeOfBinding, String isbNumber) {
        this.shoppingChannel = shoppingChannel;
        this.typeOfBinding = typeOfBinding;
        this.isbNumber = isbNumber;
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

    public String getShoppingChannel() {
        return shoppingChannel;
    }

    public void setShoppingChannel(String shoppingChannel) {
        this.shoppingChannel = shoppingChannel;
    }

    public String getTypeOfBinding() {
        return typeOfBinding;
    }

    public void setTypeOfBinding(String typeOfBinding) {
        this.typeOfBinding = typeOfBinding;
    }

    public String getIsbNumber() {
        return isbNumber;
    }

    public void setIsbNumber(String isbNumber) {
        this.isbNumber = isbNumber;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getShoppingUrl() {
        return shoppingUrl;
    }

    public void setShoppingUrl(String shoppingUrl) {
        this.shoppingUrl = shoppingUrl;
    }

    @Override
    public String toString() {
        return "BookDetail{" +
                "shoppingChannel='" + shoppingChannel + '\'' +
                ", typeOfBinding='" + typeOfBinding + '\'' +
                ", isbNumber='" + isbNumber + '\'' +
                '}';
    }
}
