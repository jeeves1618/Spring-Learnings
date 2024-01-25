package net.myphenotype.Librarian.Entity;

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

    private Integer id;
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
    private String imageFileName;
    private String readStatus;
    private String dateOfReading;
    private Integer ratingOfUsefulness;
    private String shoppingChannel;
    private String typeOfBinding;
    private String isbNumber;

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
}
