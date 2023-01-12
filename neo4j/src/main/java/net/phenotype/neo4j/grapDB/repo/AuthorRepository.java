package net.phenotype.neo4j.grapDB.repo;

import net.phenotype.neo4j.grapDB.entity.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {

    public List<Author> findByFirstNameAndLastName(String firstName, String lastName);

    public List<Author> findByFirstNameOrLastName(String firstName, String lastName);

    public List<Author> findByBornIn(List<Integer> born);

}
