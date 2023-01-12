package net.phenotype.neo4j.grapDB.entity;


import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

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

    @Relationship(type = "LIVES_IN", direction = Relationship.Direction.OUTGOING)
    private Location location;

    @Relationship(type = "HAS_WRITTEN", direction = Relationship.Direction.OUTGOING)
    private List<HasWrittenRelation> hasWrittenRelationList;

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<HasWrittenRelation> getHasWrittenList() {
        return hasWrittenRelationList;
    }

    public void setHasWrittenList(List<HasWrittenRelation> hasWrittenRelationList) {
        this.hasWrittenRelationList = hasWrittenRelationList;
    }
}
