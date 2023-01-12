package net.phenotype.neo4j.grapDB.entity;

import org.springframework.data.neo4j.core.schema.*;

@RelationshipProperties
public class HasWrittenRelation {

    @Id
    @GeneratedValue
    private Long id;

    @Property(name = "pages")
    private Long pages;

    @TargetNode
    private Book book;

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
