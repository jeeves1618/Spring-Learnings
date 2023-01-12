package net.phenotype.neo4j.grapDB.domain;

public class CreateBookRequest {

    private String title;
    private String publisher;
    private Long pages;
    private Double priceOfBook;

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

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Double getPriceOfBook() {
        return priceOfBook;
    }

    public void setPriceOfBook(Double priceOfBook) {
        this.priceOfBook = priceOfBook;
    }

    @Override
    public String toString() {
        return "CreateBookRequest{" +
                "title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", pages=" + pages +
                ", priceOfBook=" + priceOfBook +
                '}';
    }
}
