package net.phenotype.neo4j.grapDB.repo;

import net.phenotype.neo4j.grapDB.entity.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {
}
