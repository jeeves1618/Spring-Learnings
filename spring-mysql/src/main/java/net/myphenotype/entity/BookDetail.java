package net.myphenotype.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
public class BookDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String shoppingChannel;
    private String typeOfBinding;
    private String isbNumber;

    public BookDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
}
