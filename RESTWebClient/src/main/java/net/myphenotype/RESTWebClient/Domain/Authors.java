package net.myphenotype.RESTWebClient.Domain;

import org.springframework.stereotype.Component;

@Component
public class Authors {

    private int Id;
    private String authorsFirstName;
    private String authorsLastName;

    public Authors() {
    }

    public Authors(String authorsFirstName, String authorsLastName) {
        this.authorsFirstName = authorsFirstName;
        this.authorsLastName = authorsLastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getAuthorsFirstName() {
        return authorsFirstName;
    }

    public void setAuthorsFirstName(String authorsFirstName) {
        this.authorsFirstName = authorsFirstName;
    }

    public String getAuthorsLastName() {
        return authorsLastName;
    }

    public void setAuthorsLastName(String authorsLastName) {
        this.authorsLastName = authorsLastName;
    }

    @Override
    public String toString() {
        return "Authors{" +
                "Id=" + Id +
                ", authorsFirstName='" + authorsFirstName + '\'' +
                ", authorsLastName='" + authorsLastName + '\'' +
                '}';
    }
}
