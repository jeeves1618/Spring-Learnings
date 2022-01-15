package Librarian.BookManager.Entity;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
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
    @OneToOne(mappedBy = "bookDetail", cascade = CascadeType.ALL)
    private Book book;

    public BookDetail() {
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

    @Override
    public String toString() {
        return "BookDetail{" +
                "shoppingChannel='" + shoppingChannel + '\'' +
                ", typeOfBinding='" + typeOfBinding + '\'' +
                ", isbNumber='" + isbNumber + '\'' +
                '}';
    }
}
