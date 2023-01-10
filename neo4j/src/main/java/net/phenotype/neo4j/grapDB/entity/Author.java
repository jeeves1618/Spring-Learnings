package net.phenotype.neo4j.grapDB.entity;

import jakarta.persistence.Entity;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.stereotype.Component;

@Component
@Entity
@Node(labels = {"Author"})
public class Author {

    @Id
    @GeneratedValue
    private Long id;
    @Property(name = "firstName")
    private String firstName;
    @Property(name = "lastName")
    private String lastName;
    @Property(name = "born")
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
}
