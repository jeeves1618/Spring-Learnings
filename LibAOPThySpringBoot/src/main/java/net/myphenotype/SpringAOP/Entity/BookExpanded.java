package net.myphenotype.SpringAOP.Entity;

/*
Why am I using JPA annotations instead of Hibernate annotations here?

JPA (Java Persistence API) is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations. The Hibernate team recommends the use of JPA annotations as a best practice.
 */

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Data
@Scope(value = "prototype")
public class BookExpanded {

    private int id;
    private String bookTitle;
    private String bookGenre;
    private String authorFirstName;
    private String authorLastName;
    private String publisherName;
    private String dateOfPurchase;
    private double costOfPurchase;
    private String currencyCode;
    private String contactEmail;
    private double costInLocalCurrency;
    private String costInLocalCurrencyFmtd;

    private String shoppingChannel;
    private String typeOfBinding;
    private String isbNumber;

    private String authorsFirstName;
    private String authorsLastName;
    private String aboutAuthor;
}
