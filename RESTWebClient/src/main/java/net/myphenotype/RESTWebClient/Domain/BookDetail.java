package net.myphenotype.RESTWebClient.Domain;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BookDetail {

    private int id;
    private String shoppingChannel;
    private String typeOfBinding;
    private String isbNumber;

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

    @Override
    public String toString() {
        return "BookDetail{" +
                "shoppingChannel='" + shoppingChannel + '\'' +
                ", typeOfBinding='" + typeOfBinding + '\'' +
                ", isbNumber='" + isbNumber + '\'' +
                '}';
    }
}
