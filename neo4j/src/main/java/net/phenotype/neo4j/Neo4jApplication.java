package net.phenotype.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
@ComponentScan({"net.phenotype.neo4j.grapDB.domain","net.phenotype.neo4j.grapDB.controller","net.phenotype.neo4j.grapDB.service"})
@EntityScan("net.phenotype.neo4j.grapDB.entity")
@EnableNeo4jRepositories("net.phenotype.neo4j.grapDB.repo")
public class Neo4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Neo4jApplication.class, args);
	}
}
