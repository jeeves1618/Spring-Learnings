package net.phenotype.neo4j.grapDB.entity;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node(labels = {"Book"})
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "title")
    private String title;
    @Property(name = "publisher")
    private String publisher;
    @Property(name = "priceOfBook")
    private Double priceOfBook;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Double getPriceOfBook() {
        return priceOfBook;
    }

    public void setPriceOfBook(Double priceOfBook) {
        this.priceOfBook = priceOfBook;
    }
}
