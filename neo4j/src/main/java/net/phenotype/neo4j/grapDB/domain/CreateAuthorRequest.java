package net.phenotype.neo4j.grapDB.domain;

import java.util.List;

public class CreateAuthorRequest {
    private String firstName;
    private String lastName;
    private Integer born;
    private List<CreateBookRequest> bookList;
    private CreateLocationRequest createLocationRequest;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBorn() {
        return born;
    }

    public void setBorn(Integer born) {
        this.born = born;
    }

    public List<CreateBookRequest> getBookList() {
        return bookList;
    }

    public void setBookList(List<CreateBookRequest> bookList) {
        this.bookList = bookList;
    }

    public CreateLocationRequest getCreateLocationRequest() {
        return createLocationRequest;
    }

    public void setCreateLocationRequest(CreateLocationRequest createLocationRequest) {
        this.createLocationRequest = createLocationRequest;
    }

    @Override
    public String toString() {
        return "CreateAuthorRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", born=" + born +
                ", bookList=" + bookList +
                ", createLocationRequest=" + createLocationRequest +
                '}';
    }
}
