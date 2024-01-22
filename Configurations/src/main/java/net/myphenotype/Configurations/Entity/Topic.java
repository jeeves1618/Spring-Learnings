package net.myphenotype.Configurations.Entity;
/*
Why am I using JPA annotations instead of Hibernate annotations here?

JPA (Java Persistence API) is a standard specification. Hibernate is an implementation of the JPA specification.
Hibernate implements all of the JPA annotations. The Hibernate team recommends the use of JPA annotations as a best practice.
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Entity
@Builder
@AllArgsConstructor
@Data
@Table(name="topic")
public class Topic {
    /*
    To define the DB attributes in the Entity class for mySQL, here is the data type mapping for reference.
    https://dev.mysql.com/doc/connector-j/8.0/en/connector-j-reference-type-conversions.html
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "book_genre")
    private String bookGenre;
    @Column(name = "book_count")
    private long bookCount;
    @Column(name = "image_file_name")
    private String imageFileName;

       public Topic() {
    }
}
