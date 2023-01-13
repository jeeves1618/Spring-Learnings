package net.phenotype.neo4j.grapDB.repo;

import net.phenotype.neo4j.grapDB.entity.Book;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends Neo4jRepository<Book, Long> {

    @Query("match(bk:Book),(au:Author) where (bk)<-[:HAS_WRITTEN]-(au {firstName:$firstName,lastName:$lastName}) return bk")
    public List<Book> getBooksByAuthor(String firstName, String lastName);

}
