package net.phenotype.neo4j.grapDB.repo;

import net.phenotype.neo4j.grapDB.entity.Author;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends Neo4jRepository<Author, Long> {
}
