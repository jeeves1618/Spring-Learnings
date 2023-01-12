package net.phenotype.neo4j.grapDB.domain;

public class UpdateAuthorRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer born;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    @Override
    public String toString() {
        return "CreateAuthorRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", born=" + born +
                '}';
    }
}
