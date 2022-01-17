package Librarian.BookManager.Entity;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Articles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int Id;
    @Column(name = "articles_written")
    private String articlesWritten;
    @Column(name = "web_link")
    private String webLink;

    public Articles() {
    }

    public Articles(String articlesWritten, String webLink) {
        this.articlesWritten = articlesWritten;
        this.webLink = webLink;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getArticlesWritten() {
        return articlesWritten;
    }

    public void setArticlesWritten(String articlesWritten) {
        this.articlesWritten = articlesWritten;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "Id=" + Id +
                ", articlesWritten='" + articlesWritten + '\'' +
                ", webLink='" + webLink + '\'' +
                '}';
    }
}
